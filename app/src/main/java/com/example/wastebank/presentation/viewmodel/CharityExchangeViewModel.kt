package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.wastebank.domain.usecase.CharityExchangeUseCase

class CharityExchangeViewModel(private val charityExchangeUseCase: CharityExchangeUseCase) : ViewModel() {
    fun exchangeCharity(point: Int, charityName: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            charityExchangeUseCase.exchangeCharity(point, charityName, onResult)
        }
    }
}