package com.example.wastebank.data.repository

import com.example.wastebank.data.model.MoneyExchange
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.MoneyExchangeRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

// kelas implementasi dari MoneyExchangeRepository
class MoneyExchangeRepositoryImpl : MoneyExchangeRepository {

    // inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    // fungsi untuk menukarkan poin ke uang
    override suspend fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit) {
        val userId = auth.currentUser?.uid

        // memeriksa apakah pengguna telah login
        if (userId != null) {

            // membuat id unik untuk penukaran uang
            val id = UUID.randomUUID().toString()
            val rupiahAmount = point * 10

            // format tanggal dan jam
            val timestamp = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = dateFormat.format(Date(timestamp))
            val hour = timeFormat.format(Date(timestamp))

            // membuat objek MoneyExchange
            val moneyExchange = MoneyExchange(id, userId, point, rupiahAmount, bankName, accountNumber, date, hour)

            val userRef = db.getReference("users")
            val moneyExchangeRef = db.getReference("exchange").child("money").child(id)

            // menyimpan data penukaran uang ke database
            moneyExchangeRef.setValue(moneyExchange)
                .addOnSuccessListener {

                    // mendapatkan data poin pengguna saat ini
                    userRef.child(userId).child("points").get()
                        .addOnSuccessListener { snapshot ->
                            val currentPoints = snapshot.getValue(Int::class.java) ?: 0

                            // memeriksa apakah pengguna memiliki cukup poin untuk melakukan penukaran
                            if (point > currentPoints) {
                                onResult(false, "Maaf, poin anda tidak mencukupi")
                            } else {

                                // mengurangi poin pengguna sesuai dengan yang ditukarkan
                                val updatedPoints = currentPoints - point

                                // memperbarui jumlah poin pengguna di database
                                userRef.child(userId).child("points").setValue(updatedPoints)
                                    .addOnSuccessListener {
                                        onResult(true, null)
                                    }
                                    .addOnFailureListener { exception ->
                                        onResult(false, exception.message)
                                    }
                            }
                        }
                }
                .addOnFailureListener { exception ->
                    onResult(false, exception.message)
                }
        } else {
            onResult(false, "User tidak ditemukan")
        }
    }
}
