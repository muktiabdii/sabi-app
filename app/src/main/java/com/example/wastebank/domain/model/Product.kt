package com.example.wastebank.domain.model

import androidx.annotation.DrawableRes

data class Product(
    val name: String,
    val category: ProductCategory,
    val price: String,
    @DrawableRes val imageRes: Int
)
