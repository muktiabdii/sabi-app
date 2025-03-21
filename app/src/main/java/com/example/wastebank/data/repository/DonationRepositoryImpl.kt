package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.DonationMapper
import com.example.wastebank.data.model.DonationData
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.model.DonateDomain
import com.example.wastebank.domain.model.DonationDomain
import com.example.wastebank.domain.repository.DonationRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// kelas implementasi dari DonationRepository
class DonationRepositoryImpl : DonationRepository {

    // inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    // fungsi untuk mendapatkan semua donation
    override fun getAllDonation(): Flow<List<DonationDomain>> = callbackFlow {
        val donationRef = db.getReference("donation")
        val listener = object : ValueEventListener {

            // jika ada perubahan data, kirimkan ke flow
            override fun onDataChange(snapshot: DataSnapshot) {
                val donationList = snapshot.children.mapNotNull { dataSnapshot ->
                    val donationData = dataSnapshot.getValue(DonationData::class.java)
                    donationData?.let { DonationMapper.mapToDomain(it) }
                }
                trySend(donationList).isSuccess
            }

            // jika terjadi error, kirimkan ke flow
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        // tambahkan listener ke referensi Firebase Realtime Database
        donationRef.addValueEventListener(listener)

        awaitClose { donationRef.removeEventListener(listener) }
    }.catch { emit(emptyList()) }

    // fungsi untuk mendapatkan donation berdasarkan judul
    override suspend fun getDonationByTitle(title: String): DonationDomain? {
        return try {
            val snapshot = db.getReference("donation").get().await()
            val donation = snapshot.children.mapNotNull { dataSnapshot ->
                val donationData = dataSnapshot.getValue(DonationData::class.java)
                donationData?.let { DonationMapper.mapToDomain(it) }
        }.firstOrNull { it.title == title }

            // jika ditemukan donation dengan judul yang sesuai, kirimkan ke flow
            donation
        } catch (e: Exception) {
            null
        }
    }

    // fungsi untuk melakukan donasi
    override suspend fun donate(donate: DonateDomain): Result<Boolean> {
        return try{
            val currentUser = auth.currentUser ?: return Result.failure(Exception("User belum login"))

            // mendapatkan user info
            val userRef = db.getReference("users").child(currentUser.uid)
            val userName = db.getReference("users").child(currentUser.uid).child("name").get().await().getValue(String::class.java) ?: "Unknown"

            // mendapatkan jumlah poin user
            val userPoints = userRef.child("points").get().await().getValue(Int::class.java) ?: 0
            val requiredPoints = (donate.totalPoints ?: 0) / 10

            // jika jumlah poin user kurang dari jumlah poin yang dibutuhkan, gagalkan donasi
            val donateRef = db.getReference("donates").child(donate.donateMethod).push()
            val donateId = donateRef.key ?: return Result.failure(Exception("Gagal generate donateId"))

            // jika jumlah poin user kurang dari jumlah poin yang dibutuhkan, gagalkan donasi
            val timestamp = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = dateFormat.format(Date(timestamp))
            val hour = timeFormat.format(Date(timestamp))

            // buat objek DonateDomain dengan ID & User Info
            val updatedDonate = donate.copy(
                donateId = donateId,
                userId = currentUser.uid,
                userName = userName,
                date = date,
                hour = hour,
                totalPoints = if (donate.donateMethod == "points") requiredPoints else null,
                totalAmount = if (donate.donateMethod == "points") null else donate.totalAmount
            )

            donateRef.setValue(updatedDonate).await()

            // jika metode donasi adalah points, kurangi jumlah poin user
            if (donate.donateMethod == "points") {
                val newPoints = userPoints - requiredPoints
                userRef.child("points").setValue(newPoints).await()
            }

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
