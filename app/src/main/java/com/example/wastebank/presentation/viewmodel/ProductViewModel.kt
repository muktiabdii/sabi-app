package com.example.wastebank.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.data.model.PaymentData
import com.example.wastebank.domain.model.CartItemDomain
import com.example.wastebank.domain.model.PaymentDomain
import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.domain.usecase.ProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductViewModel(private val productUseCase: ProductUseCase) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductDomain>>(emptyList())
    val products: StateFlow<List<ProductDomain>> = _products.asStateFlow()

    private val _cartProducts = MutableStateFlow<List<CartItemDomain>>(emptyList())
    val cartProducts: StateFlow<List<CartItemDomain>> = _cartProducts.asStateFlow()

    private val _selectedProduct = MutableStateFlow<ProductDomain?>(null)
    val selectedProduct: StateFlow<ProductDomain?> = _selectedProduct.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _paymentState = MutableStateFlow<Result<Boolean>?>(null)
    val paymentState: StateFlow<Result<Boolean>?> = _paymentState.asStateFlow()

    private val _proofImageUrl = MutableStateFlow<String?>(null)
    val proofImageUrl: StateFlow<String?> = _proofImageUrl

    fun setProofImageUrl(url: String) {
        _proofImageUrl.value = url
    }

    fun resetProofImageUrl() {
        _proofImageUrl.value = null
    }

    fun getProducts() {
        viewModelScope.launch {
            val result = productUseCase.getProducts()
            _products.value = result
        }
    }

    fun getProductByName(name: String) {
        viewModelScope.launch {
            val result = productUseCase.getProductByName(name)
            _selectedProduct.value = result
        }
    }

    fun addToCart(product: ProductDomain) {
        viewModelScope.launch {
            val result = productUseCase.addToCart(product)
            result.onSuccess {
                _errorMessage.value = null
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
        }
    }

    fun getCartItems() {
        viewModelScope.launch {
            productUseCase.getCartItems()
                .collectLatest { result ->
                    _cartProducts.value = result
                }
        }
    }


    fun removeFromCart(productId: String) {
        viewModelScope.launch {
            val result = productUseCase.removeFromCart(productId)
            result.onSuccess {
                _errorMessage.value = null
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
        }
    }

    fun payment(selectedOption: String) {
        viewModelScope.launch {
            _paymentState.value = Result.success(false) // Menandakan proses sedang berjalan

            val proofUrl = _proofImageUrl.value
            val cartItems = _cartProducts.value
            if (cartItems.isEmpty()) {
                _errorMessage.value = "Keranjang belanja kosong!"
                return@launch
            }

            val paymentData = PaymentDomain(
                paymentMethod = if (selectedOption == "Transfer Bank") "money" else "points",
                items = cartItems, // Kirim semua produk yang ada di cart
                totalAmount = cartItems.sumOf { it.price },
                receiptImage = proofUrl ?: ""
            )

            val result = productUseCase.payment(paymentData)
            _paymentState.value = result

            result.onSuccess {
                _cartProducts.value = emptyList() // Kosongkan cart setelah pembayaran sukses
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
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
