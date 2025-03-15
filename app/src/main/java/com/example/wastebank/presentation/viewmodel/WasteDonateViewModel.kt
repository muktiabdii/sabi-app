package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.data.model.WasteItem
import com.example.wastebank.domain.usecase.WasteDonateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WasteDonateViewModel (private val wasteDonateUseCase: WasteDonateUseCase, private val authViewModel: AuthViewModel) : ViewModel() {

    val email: StateFlow<String> = authViewModel.email

    private val _isDonated = MutableStateFlow(false)
    val isDonated: StateFlow<Boolean> = _isDonated

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun donateWaste(items: List<WasteItem>) {
        viewModelScope.launch {
            wasteDonateUseCase.donateWaste(email.value, items) { success, message ->
                if (success) {
                    _isDonated.value = true
                } else {
                    _errorMessage.value = message
                }
            }
        }
    }

    fun resetDonationState() {
        _isDonated.value = false
        _errorMessage.value = null
    }

    class Factory(
        private val wasteDonateUseCase: WasteDonateUseCase,
        private val authViewModel: AuthViewModel
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WasteDonateViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WasteDonateViewModel(wasteDonateUseCase, authViewModel) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}