package com.example.wastebank.data.model

data class CartItemData(
    val name: String = "",
    val category: String = "",
    val price: Int = 0,
    val quantity: Int = 1,
    val image: String = "",
    val total: Int = 0
)
