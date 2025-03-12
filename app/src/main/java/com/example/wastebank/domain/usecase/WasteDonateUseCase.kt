package com.example.wastebank.domain.usecase

import com.example.wastebank.data.model.WasteItem
import com.example.wastebank.domain.repository.WasteDonateRepository

class WasteDonateUseCase (private val wasteDonateRepository: WasteDonateRepository) {
    fun donateWaste(email: String, items: List<WasteItem>, onResult: (Boolean, String?) -> Unit) {

        // Validasi input
        if (email.isBlank()) {
            onResult(false, "Harap masukkan email")
            return
        }

        // Validasi item sampah
        if (items.isEmpty()) {
            onResult(false, "Harap masukkan sampah yang akan didonasikan")
            return
        }

        // Validasi setiap item sampah
        items.forEach { item ->
            if (item.wasteType.isBlank() || item.quantity <= 0) {
                onResult(false, "Harap masukkan jenis sampah dan jumlah yang valid")
                return
            }
        }

        // Validasi email
        if (!validateEmail(email)) {
            onResult(false, "Email tidak valid")
            return
        }

        wasteDonateRepository.donateWaste(email, items, onResult)
    }

    // Fungsi untuk melakukan validasi email
    private fun validateEmail(email: String): Boolean {
        return email.contains("@")
    }
}