package com.example.wastebank.data.repository

import com.example.wastebank.data.model.CharityExchange
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.CharityExchangeRepository
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID

class CharityExchangeRepositoryImpl : CharityExchangeRepository {

    // Inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    override suspend fun exchangeCharity(point: Int, charityName: String, onResult: (Boolean, String?) -> Unit) {
        val userId = auth.currentUser?.uid
        if(userId != null) {
            val id = UUID.randomUUID().toString()
            val timestamp = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = dateFormat.format(timestamp)
            val hour = timeFormat.format(timestamp)

            val charityExchange = CharityExchange(id, userId, point, charityName, date, hour)

            val userRef = db.getReference("users")
            val charityExchangeRef = db.getReference("charityExchange").child("charity").child(id)
            charityExchangeRef.setValue(charityExchange)
                .addOnSuccessListener {
                    userRef.child(userId).child("points").get()
                        .addOnSuccessListener { snapshot ->
                            val currentPoints = snapshot.getValue(Int::class.java) ?: 0
                            val updatedPoints = currentPoints - point

                            userRef.child(userId).child("points").setValue(updatedPoints)
                                .addOnSuccessListener {
                                    onResult(true, null)
                                }
                                .addOnFailureListener { exception ->
                                    onResult(false, exception.message)
                                }
                        }
                }
                .addOnFailureListener { exception ->
                    onResult(false, exception.message)
                }
        }

        else {
            onResult(false, "User tidak ditemukan")
        }
    }
}