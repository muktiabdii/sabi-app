package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.ProductMapper
import com.example.wastebank.data.model.CartItemData
import com.example.wastebank.data.model.ProductData
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.model.CartItemDomain
import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.domain.repository.ProductRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ProductRepositoryImpl : ProductRepository {

    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    override suspend fun getProducts(): List<ProductDomain> {
        return try {
            val snapshot = db.getReference("product").get().await()
            snapshot.children.mapNotNull { dataSnapshot ->
                val productData = dataSnapshot.getValue(ProductData::class.java)
                productData?.let { ProductMapper.mapToDomain(it) }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getProductByName(name: String): ProductDomain? {
        return try {
            val snapshot = db.getReference("product").get().await()
            val product = snapshot.children.mapNotNull { dataSnapshot ->
                val productData = dataSnapshot.getValue(ProductData::class.java)
                productData?.let { ProductMapper.mapToDomain(it) }
            }.firstOrNull { it.name == name } // Cari produk berdasarkan nama

            product
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun addToCart(product: ProductDomain): Result<Unit> {
        val currentUser = auth.currentUser ?: return Result.failure(Exception("User not logged in"))
        val userId = currentUser.uid
        val cartRef = db.getReference("users").child(userId).child("cart").child(product.name)

        return try {
            val snapshot = cartRef.get().await()
            val currentQuantity = snapshot.child("quantity").getValue(Int::class.java) ?: 0
            val newQuantity = currentQuantity + 1

            val cartItemData = CartItemData(
                name = product.name,
                category = product.category,
                price = product.price,
                quantity = newQuantity,
                image = product.image,
                total = product.price * newQuantity
            )

            cartRef.setValue(cartItemData).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCartItems(): Flow<List<CartItemDomain>> = callbackFlow {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            close() // Tutup Flow jika user belum login
            return@callbackFlow
        }

        val userId = currentUser.uid
        val cartRef = db.getReference("users").child(userId).child("cart")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val cartItems = snapshot.children.mapNotNull { dataSnapshot ->
                    val cartItem = dataSnapshot.getValue(CartItemData::class.java)
                    cartItem?.let {
                        CartItemDomain(
                            name = it.name,
                            category = it.category,
                            price = it.price,
                            quantity = it.quantity,
                            image = it.image,
                            total = it.total
                        )
                    }
                }
                trySend(cartItems).isSuccess // Kirim data terbaru ke Flow
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException()) // Tutup Flow jika error
            }
        }

        cartRef.addValueEventListener(listener)

        awaitClose { cartRef.removeEventListener(listener) } // Hapus listener saat tidak digunakan
    }


    override suspend fun removeFromCart(productId: String): Result<Unit> {
        val currentUser = auth.currentUser ?: return Result.failure(Exception("User not logged in"))
        val userId = currentUser.uid
        val cartRef = db.getReference("users").child(userId).child("cart").child(productId)

        return try {
            cartRef.removeValue().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
