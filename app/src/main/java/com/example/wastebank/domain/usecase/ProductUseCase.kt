package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.domain.repository.ProductRepository

class ProductUseCase(private val productRepository: ProductRepository) {

    suspend fun getProducts(): List<ProductDomain> {
        return productRepository.getProducts()
    }

    suspend fun getProductByName(name: String): ProductDomain? {
        return productRepository.getProductByName(name)
    }
}
