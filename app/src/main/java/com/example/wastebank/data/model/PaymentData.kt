package com.example.wastebank.data.model

data class PaymentData(
    val paymentId: String = "",
    val userId: String = "",
    val userName: String = "",
    val productName: String = "",
    val productPrice: Int = 0,
    val productQuantity: Int = 0,
    val paymentMethod: String = "Money",
    val totalPoints: Int? = null,
    val totalAmount: Int? = null,
    val status: String = "Pending",
    val date: String = "",
    val hour: String = "",
    val receiptImage: String? = null
)
