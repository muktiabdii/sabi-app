package com.example.wastebank.domain.model

// kategori produk marketplace
enum class ProductCategory(val displayName: String) {
    VASE("Vas Bunga"),
    FASHION("Fashion Wear"),
    CRAFT("Prakarya"),
    TOY("Mainan"),
    DECOR("Hiasan/Dekorasi");

    override fun toString(): String {
        return displayName
    }
}