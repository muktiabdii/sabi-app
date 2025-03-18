package com.example.wastebank.domain.model

data class PaymentDomain(
    val paymentId: String = "",
    val userId: String = "",
    val userName: String = "",
    val items: List<CartItemDomain> = emptyList(),
    val paymentMethod: String = "Money",
    val totalPoints: Int? = null,
    val totalAmount: Int? = null,
    val status: String = "Pending",
    val date: String = "",
    val hour: String = "",
    val receiptImage: String? = null
)
