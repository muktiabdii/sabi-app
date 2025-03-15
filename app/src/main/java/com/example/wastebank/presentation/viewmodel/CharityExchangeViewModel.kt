package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wastebank.domain.usecase.CharityExchangeUseCase

class CharityExchangeViewModel(private val charityExchangeUseCase: CharityExchangeUseCase) : ViewModel() {
    fun exchangeCharity(point: Int, charityName: String, onResult: (Boolean, String?) -> Unit) {
        charityExchangeUseCase.exchangeCharity(point, charityName, onResult)
    }
}