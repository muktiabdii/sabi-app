package com.example.wastebank.domain.repository

interface CharityExchangeRepository {
    fun exchangeCharity(point: Int, charityName: String, onResult: (Boolean, String?) -> Unit)
}