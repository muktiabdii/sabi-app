package com.example.wastebank.domain.repository

interface MoneyExchangeRepository {
    fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit)
}