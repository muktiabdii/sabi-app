package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.DonationMapper
import com.example.wastebank.data.model.DonationData
import com.example.wastebank.data.source.firebase.FirebaseService
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

class DonationRepositoryImpl : DonationRepository {

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
}
