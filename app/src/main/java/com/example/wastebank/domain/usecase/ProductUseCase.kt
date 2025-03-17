package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.domain.repository.ProductRepository

class ProductUseCase(private val repository: ProductRepository) {

    suspend fun getProducts(): List<ProductDomain> {
        return repository.getProducts()
    }
}
