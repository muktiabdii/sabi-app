package com.example.wastebank.data.repository

import com.example.wastebank.data.model.MoneyExchange
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.MoneyExchangeRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class MoneyExchangeRepositoryImpl : MoneyExchangeRepository {

    // Inisialisasi Firebase Auth dan Firebase Realtime Database untuk autentikasi pengguna dan akses database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    /**
     * Fungsi untuk melakukan proses penukaran uang berdasarkan poin yang dimiliki oleh pengguna.
     * Fungsi ini akan mengurangi poin pengguna sesuai jumlah poin yang ditukarkan dan menyimpan transaksi penukaran uang ke Firebase.
     * Jika berhasil, poin pengguna akan diperbarui di database.
     * Jika gagal, pesan error akan dikirimkan.
     */
    override suspend fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit) {
        val userId = auth.currentUser?.uid // Mendapatkan ID pengguna yang sedang login

        // Memeriksa apakah pengguna telah login
        if (userId != null) {
            val id = UUID.randomUUID().toString() // Membuat ID unik untuk penukaran uang
            val rupiahAmount = point * 10 // Menghitung jumlah uang berdasarkan poin yang ditukarkan

            // Format tanggal dan jam untuk transaksi
            val timestamp = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = dateFormat.format(Date(timestamp))
            val hour = timeFormat.format(Date(timestamp))

            // Membuat objek MoneyExchange yang berisi informasi transaksi penukaran uang
            val moneyExchange = MoneyExchange(id, userId, point, rupiahAmount, bankName, accountNumber, date, hour)

            val userRef = db.getReference("users") // Referensi untuk data pengguna di database
            val moneyExchangeRef = db.getReference("exchange").child("money").child(id) // Referensi untuk data penukaran uang di database

            // Menyimpan data penukaran uang ke Firebase Realtime Database
            moneyExchangeRef.setValue(moneyExchange)
                .addOnSuccessListener {
                    // Mendapatkan data poin pengguna saat ini
                    userRef.child(userId).child("points").get()
                        .addOnSuccessListener { snapshot ->
                            val currentPoints = snapshot.getValue(Int::class.java) ?: 0

                            // Memeriksa apakah pengguna memiliki cukup poin untuk melakukan penukaran
                            if (point > currentPoints) {
                                onResult(false, "Maaf, poin anda tidak mencukupi") // Poin tidak mencukupi
                            } else {
                                // Mengurangi poin pengguna sesuai dengan yang ditukarkan
                                val updatedPoints = currentPoints - point

                                // Memperbarui jumlah poin pengguna di database
                                userRef.child(userId).child("points").setValue(updatedPoints)
                                    .addOnSuccessListener {
                                        onResult(true, null) // Penukaran berhasil
                                    }
                                    .addOnFailureListener { exception ->
                                        onResult(false, exception.message) // Penukaran gagal
                                    }
                            }
                        }
                }
                .addOnFailureListener { exception ->
                    onResult(false, exception.message) // Gagal menyimpan transaksi penukaran
                }
        } else {
            onResult(false, "User tidak ditemukan") // Pengguna tidak ditemukan
        }
    }
}
