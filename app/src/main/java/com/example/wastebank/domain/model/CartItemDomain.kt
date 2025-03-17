package com.example.wastebank.domain.model

import java.text.NumberFormat
import java.util.Locale

data class CartItemDomain(
    val name: String = "",
    val category: String = "",
    val price: Int = 0,
    val quantity: Int = 1,
    val image: String = "",
    val total: Int = 0
){
    fun formatRupiah(): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return format.format(price).replace(",00", "")
    }
}
