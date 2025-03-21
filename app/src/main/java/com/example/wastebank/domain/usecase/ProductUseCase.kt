package com.example.wastebank.domain.usecase

import com.example.wastebank.data.model.PaymentData
import com.example.wastebank.domain.model.CartItemDomain
import com.example.wastebank.domain.model.PaymentDomain
import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductUseCase(private val productRepository: ProductRepository) {

    suspend fun getProducts(): List<ProductDomain> {
        return productRepository.getProducts()
    }

    suspend fun getProductByName(name: String): ProductDomain? {
        return productRepository.getProductByName(name)
    }

    suspend fun addToCart(product: ProductDomain): Result<Unit> {
        return productRepository.addToCart(product)
    }

    fun getCartItems(): Flow<List<CartItemDomain>> {
        return productRepository.getCartItems()
    }

    suspend fun removeFromCart(productId: String): Result<Unit> {
        return productRepository.removeFromCart(productId)
    }

    suspend fun payment(payment: PaymentDomain): Result<Boolean> {
        return productRepository.payment(payment)
    }
}
