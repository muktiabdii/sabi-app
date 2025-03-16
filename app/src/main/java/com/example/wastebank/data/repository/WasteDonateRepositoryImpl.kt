package com.example.wastebank.data.repository

import com.example.wastebank.data.model.WasteDonateModel
import com.example.wastebank.data.model.WasteItem
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.WasteDonateRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

// Implementasi dari WasteDonateRepository
class WasteDonateRepositoryImpl : WasteDonateRepository{

    // Inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    // Fungsi untuk melakukan donasi sampah
    override suspend fun donateWaste(email: String, items: List<WasteItem>, onResult: (Boolean, String?) -> Unit) {

        val userRef = db.getReference("users")

        // Cari userId berdasarkan email
        userRef.orderByChild("email").equalTo(email).get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.exists()) {
                    onResult(false, "User tidak ditemukan")
                    return@addOnSuccessListener
                }

                // Ambil userId dari snapshot
                val userId = snapshot.children.first().key ?: return@addOnSuccessListener

                // Generate id unik
                val id = UUID.randomUUID().toString()

                // Ambil timestamp saat ini
                val timestamp = System.currentTimeMillis()
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val date = dateFormat.format(Date(timestamp))
                val hour = timeFormat.format(Date(timestamp))

                // Hitung total poin
                val totalPoints = items.sumOf { calculatePoints(it.wasteType, it.quantity) }

                // Buat objek WasteDonateModel
                val donationsData = WasteDonateModel(id, userId, date, hour, items, totalPoints)

                val donationsRef = db.getReference("donations").child(id)
                val userPoinRef = userRef.child(userId).child("points")

                // Simpan di database donations
                donationsRef.setValue(donationsData)
                    .addOnSuccessListener {

                        // Update poin pengguna
                        userPoinRef.get()
                            .addOnSuccessListener { userSnapshot ->
                                val currentPoints = userSnapshot.getValue(Long::class.java) ?: 0
                                userPoinRef.setValue(currentPoints + donationsData.points)
                                    .addOnSuccessListener {
                                        onResult(true, null)
                                    }

                                    .addOnFailureListener { exception ->
                                        onResult(false, exception.message)
                                    }
                            }
                            .addOnFailureListener { exception ->
                                onResult(false, exception.message)
                            }
                    }
                    .addOnFailureListener { exception ->
                        onResult(false, exception.message)
                    }
            }
            .addOnFailureListener { exception ->
                onResult(false, exception.message)
            }
    }

    private fun calculatePoints(wasteType: String, quantity: Int): Int {
        return when (wasteType.lowercase()) {
            "plastik" -> (quantity * 10)
            "kertas" -> (quantity * 5)
            "logam" -> (quantity * 15)
            else -> (quantity * 2)
        }
    }
}