package com.example.wastebank.domain.model

data class DonateDomain(
    val donateId: String = "",
    val userId: String = "",
    val userName: String = "",
    val donations: DonationDomain = DonationDomain(),
    val donateMethod: String = "",
    val totalPoints: Int? = null,
    val totalAmount: Int? = null,
    val status: String = "Pending",
    val date: String = "",
    val hour: String = "",
    val receiptImage: String
)
