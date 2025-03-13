package com.example.wastebank.data.repository

import com.example.wastebank.data.model.MoneyExchange
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.MoneyExchangeRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class MoneyExchangeRepositoryImpl : MoneyExchangeRepository {

    // Inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    override fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val id = UUID.randomUUID().toString()
            val rupiahAmount = point

            val timestamp = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = dateFormat.format(Date(timestamp))
            val hour = timeFormat.format(Date(timestamp))

            val moneyExchange = MoneyExchange(id, userId, point, rupiahAmount, bankName, accountNumber, date, hour)

            val userRef = db.getReference("users")
            val moneyExchangeRef = db.getReference("moneyExchange").child("money").child(id)
            moneyExchangeRef.setValue(moneyExchange)
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