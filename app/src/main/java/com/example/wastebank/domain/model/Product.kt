package com.example.wastebank.domain.model

import androidx.annotation.DrawableRes
import java.text.NumberFormat
import java.util.Locale

data class Product(
    val name: String,
    val category: ProductCategory,
    val price: Int,
    @DrawableRes val imageRes: Int,
    val description: String
) {
    val formattedPrice: String
        get() = "Rp " + NumberFormat.getNumberInstance(Locale("id", "ID")).format(price)

    val pointsEquivalent: Int
        get() = price / 10
}
