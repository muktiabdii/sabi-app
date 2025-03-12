package com.example.wastebank.data.model

// Model data donasi sampah
data class WasteDonateModel(
    val id: String = "",
    val userId: String = "",
    val date: String = "",
    val hour: String = "",
    val items: List<WasteItem> = emptyList(),
    val points: Int = 0
)
