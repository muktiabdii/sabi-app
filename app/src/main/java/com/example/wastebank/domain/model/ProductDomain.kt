package com.example.wastebank.domain.model

import java.text.NumberFormat
import java.util.Locale

data class ProductDomain(
    val name: String,
    val category: String,
    val price: Int,
    val image: String,
    val description: String
){
    fun formatRupiah(): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return format.format(price).replace(",00", "")
    }
}
