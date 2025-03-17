package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.ProductMapper
import com.example.wastebank.data.model.ProductData
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.domain.repository.ProductRepository
import kotlinx.coroutines.tasks.await

class ProductRepositoryImpl : ProductRepository {

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

}
