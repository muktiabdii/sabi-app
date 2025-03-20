package com.example.wastebank.data.model

data class DonateData(
    val donateId: String = "",
    val userId: String = "",
    val userName: String = "",
    val instanceName: String = "",
    val instanceLocation: String = "",
    val accountName: String = "",
    val accountNumber: String = "",
    val bank: String = "",
    val donateMethod: String = "Money",
    val totalPoints: Int? = null,
    val totalAmount: Int? = null,
    val status: String = "Pending",
    val date: String = "",
    val hour: String = "",
    val receiptImage: String
)
