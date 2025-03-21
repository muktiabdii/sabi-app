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

class DonationRepositoryImpl : DonationRepository {

    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    override fun getAllDonation(): Flow<List<DonationDomain>> = callbackFlow {
        val donationRef = db.getReference("donation")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val donationList = snapshot.children.mapNotNull { dataSnapshot ->
                    val donationData = dataSnapshot.getValue(DonationData::class.java)
                    donationData?.let { DonationMapper.mapToDomain(it) }
                }
                trySend(donationList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException()) // Tutup flow jika terjadi error
            }
        }

        donationRef.addValueEventListener(listener)

        awaitClose { donationRef.removeEventListener(listener) } // Hapus listener saat tidak digunakan
    }.catch { emit(emptyList()) } // Jika terjadi error, kembalikan list kosong

    override suspend fun getDonationByTitle(title: String): DonationDomain? {
        return try {
            val snapshot = db.getReference("donation").get().await()
            val donation = snapshot.children.mapNotNull { dataSnapshot ->
                val donationData = dataSnapshot.getValue(DonationData::class.java)
                donationData?.let { DonationMapper.mapToDomain(it) }
        }.firstOrNull { it.title == title } // Cari donation berdasarkan nama

            donation
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun donate(donate: DonateDomain): Result<Boolean> {
        return try{
            val currentUser = auth.currentUser ?: return Result.failure(Exception("User belum login"))
            val userRef = db.getReference("users").child(currentUser.uid)
            val userName = db.getReference("users").child(currentUser.uid).child("name").get().await().getValue(String::class.java) ?: "Unknown"

            val userPoints = userRef.child("points").get().await().getValue(Int::class.java) ?: 0
            val requiredPoints = (donate.totalAmount ?: 0) / 10

            val donateRef = db.getReference("donates").child(donate.donateMethod).push()
            val donateId = donateRef.key ?: return Result.failure(Exception("Gagal generate donateId"))

            val timestamp = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = dateFormat.format(Date(timestamp))
            val hour = timeFormat.format(Date(timestamp))

            // Buat objek DonateDomain dengan ID & User Info
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

            if (donate.donateMethod == "points") {
                val newPoints = userPoints - requiredPoints
                userRef.child("points").setValue(newPoints).await() // Update jumlah poin user
            }

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
