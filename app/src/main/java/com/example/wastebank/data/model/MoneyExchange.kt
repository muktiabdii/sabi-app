package com.example.wastebank.data.model

data class MoneyExchange(
    val id: String = "",
    val userId: String = "",
    val points: Int = 0,
    val rupiahAmount: Int = 0,
    val bankName: String = "",
    val accountNumber: String = "",
    val date: String = "",
    val hour: String = ""
)
