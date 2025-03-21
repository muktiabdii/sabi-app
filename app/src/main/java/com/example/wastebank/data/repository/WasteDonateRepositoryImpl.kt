package com.example.wastebank.data.repository

import com.example.wastebank.data.model.WasteDonateModel
import com.example.wastebank.data.model.WasteItem
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.WasteDonateRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class WasteDonateRepositoryImpl : WasteDonateRepository{

    private val db = FirebaseService.db

    override suspend fun donateWaste(email: String, items: List<WasteItem>, onResult: (Boolean, String?) -> Unit) {

        val userRef = db.getReference("users")

        userRef.orderByChild("email").equalTo(email).get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.exists()) {
                    onResult(false, "User tidak ditemukan")
                    return@addOnSuccessListener
                }

                val userId = snapshot.children.first().key ?: return@addOnSuccessListener
                val id = UUID.randomUUID().toString()

                val timestamp = System.currentTimeMillis()
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val date = dateFormat.format(Date(timestamp))
                val hour = timeFormat.format(Date(timestamp))

                val totalPoints = items.sumOf { calculatePoints(it.wasteType, it.quantity) }
                val donationsData = WasteDonateModel(id, userId, date, hour, items, totalPoints)

                val donationsRef = db.getReference("donations").child(id)
                val userPoinRef = userRef.child(userId).child("points")

                donationsRef.setValue(donationsData)
                    .addOnSuccessListener {

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