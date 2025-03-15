package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.CharityExchangeRepository

class CharityExchangeUseCase(private val charityExchangeRepository: CharityExchangeRepository) {
    fun exchangeCharity(point: Int, charityName: String, onResult: (Boolean, String?) -> Unit) {
        charityExchangeRepository.exchangeCharity(point, charityName, onResult)
    }
}