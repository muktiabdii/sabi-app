package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.domain.usecase.ProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val productUseCase: ProductUseCase) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductDomain>>(emptyList())
    val products: StateFlow<List<ProductDomain>> = _products.asStateFlow()

    fun getProducts() {
        viewModelScope.launch {
            val result = productUseCase.getProducts()
            _products.value = result
        }
    }

    class Factory(private val productUseCase: ProductUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductViewModel(productUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
