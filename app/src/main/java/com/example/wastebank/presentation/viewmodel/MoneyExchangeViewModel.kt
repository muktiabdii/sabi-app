package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.domain.usecase.MoneyExchangeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoneyExchangeViewModel(private val moneyExchangeUseCase: MoneyExchangeUseCase) : ViewModel() {

    private val _points = MutableStateFlow(0)
    val points: StateFlow<Int> = _points

    private val _bankName = MutableStateFlow("")
    val bankName: StateFlow<String> = _bankName

    private val _accountNumber = MutableStateFlow("")
    val accountNumber: StateFlow<String> = _accountNumber

    private val _amount = MutableStateFlow(0)
    val amount: StateFlow<Int> = _amount

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun clearState() {
        _points.value = 0
        _bankName.value = ""
        _accountNumber.value = ""
        _amount.value = 0
        _errorMessage.value = null
    }

    fun updatePointAndAmount(value: Int) {
        _points.value = value
        _amount.value = value * 10
    }

    fun updateBankName(value: String) { _bankName.value = value }
    fun updateAccountNumber(value: String) { _accountNumber.value = value }
    fun resetErrorMessage() { _errorMessage.value = null }

    fun exchangeMoney(onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            moneyExchangeUseCase.exchangeMoney(
                points.value, bankName.value, accountNumber.value
            ) { success, message ->
                if (success) {
                    onResult(true, "")
                } else {
                    onResult(false, "")
                    _errorMessage.value = message
                }
            }
        }
    }


    class Factory(
        private val moneyExchangeUseCase: MoneyExchangeUseCase) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MoneyExchangeViewModel::class.java)) {
                return MoneyExchangeViewModel(moneyExchangeUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class") // Jika kelas tidak dikenal
        }
    }
}
