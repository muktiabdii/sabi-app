package com.example.wastebank.domain.repository

import com.example.wastebank.data.model.WasteItem

// Repository untuk donasi sampah
interface WasteDonateRepository {
    suspend fun donateWaste(email: String, items: List<WasteItem>, onResult: (Boolean, String?) -> Unit)
}