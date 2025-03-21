package com.example.wastebank.data.repository

import com.example.wastebank.data.model.CharityExchange
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.CharityExchangeRepository
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID

// kelas implementasi dari CharityExchangeRepository
class CharityExchangeRepositoryImpl : CharityExchangeRepository {

    // inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    // fungsi untuk melakukan tukar point ke organisasi
    override suspend fun exchangeCharity(point: Int, charityName: String, onResult: (Boolean, String?) -> Unit) {
        val userId = auth.currentUser?.uid
        if(userId != null) {

            // generate ID untuk charityExchange
            val id = UUID.randomUUID().toString()

            // mendapatkan tanggal dan waktu saat ini
            val timestamp = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = dateFormat.format(timestamp)
            val hour = timeFormat.format(timestamp)

            // membuat objek CharityExchange dan menyimpannya ke Firebase Realtime Database
            val charityExchange = CharityExchange(id, userId, point, charityName, date, hour)

            // menyimpan charityExchange ke Firebase Realtime Database
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