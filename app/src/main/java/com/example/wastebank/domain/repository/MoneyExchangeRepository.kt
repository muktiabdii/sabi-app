package com.example.wastebank.domain.repository

interface MoneyExchangeRepository {
    suspend fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit)
}