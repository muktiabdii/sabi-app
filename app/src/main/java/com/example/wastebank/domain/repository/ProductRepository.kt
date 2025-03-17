package com.example.wastebank.domain.repository

import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.domain.model.CartItemDomain
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): List<ProductDomain>
    suspend fun getProductByName(name: String): ProductDomain?
    suspend fun addToCart(product: ProductDomain): Result<Unit>
    fun getCartItems(): Flow<List<CartItemDomain>>
    suspend fun removeFromCart(productId: String): Result<Unit>
}
