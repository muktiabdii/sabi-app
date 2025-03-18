package com.example.wastebank.domain.model

import java.text.NumberFormat
import java.util.Locale

data class DonationDomain(
    val title: String = "",
    val description: String = "",
    val image: String = "",
    val location: String = "",
    val accountName: String = "",
    val accountNumber: String = "",
    val bank: String = "",
    val totalAmount: String = ""
)
