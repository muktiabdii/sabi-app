package com.example.wastebank.domain.repository

interface CharityExchangeRepository {
    suspend fun exchangeCharity(point: Int, charityName: String, onResult: (Boolean, String?) -> Unit)
}