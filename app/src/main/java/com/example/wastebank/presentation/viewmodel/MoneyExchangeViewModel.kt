package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wastebank.domain.usecase.MoneyExchangeUseCase

class MoneyExchangeViewModel(private val moneyExchangeUseCase: MoneyExchangeUseCase) : ViewModel() {
    fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit) {
        moneyExchangeUseCase.exchangeMoney(point, bankName, accountNumber, onResult)
    }
}