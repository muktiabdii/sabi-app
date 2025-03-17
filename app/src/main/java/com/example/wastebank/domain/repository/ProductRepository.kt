package com.example.wastebank.domain.repository

import com.example.wastebank.domain.model.ProductDomain

interface ProductRepository {
    suspend fun getProducts(): List<ProductDomain>
    suspend fun getProductByName(name: String): ProductDomain?
}