package com.example.wastebank.domain.model

data class ProductDomain(
    val name: String,
    val category: String,
    val price: Int,
    val image: String,
    val description: String
)
