package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.MoneyExchangeRepository

class MoneyExchangeUseCase(private val moneyExchangeRepository: MoneyExchangeRepository) {
    fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit) {
        
        // Validasi input
        if (point <= 0 || bankName.isBlank() || accountNumber.isBlank()) {
            onResult(false, "Harap isi semua data")
            return
        }

        moneyExchangeRepository.exchangeMoney(point, bankName, accountNumber, onResult)
    }
}