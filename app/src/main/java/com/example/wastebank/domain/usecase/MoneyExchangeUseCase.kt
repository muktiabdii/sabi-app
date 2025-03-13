package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.MoneyExchangeRepository

class MoneyExchangeUseCase(private val moneyExchangeRepository: MoneyExchangeRepository) {
    fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit) {
        moneyExchangeRepository.exchangeMoney(point, bankName, accountNumber, onResult)
    }
}