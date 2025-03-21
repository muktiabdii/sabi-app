package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.PickupMapper.toPickupData
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.model.PickupDomain
import com.example.wastebank.domain.repository.PickupRepository
import kotlinx.coroutines.tasks.await

class PickupRepositoryImpl : PickupRepository{

    val db = FirebaseService.db
    val auth = FirebaseService.auth

    override suspend fun requestPickup(pickupDomain: PickupDomain): Result<Boolean> {
        return try {
            val userEmail = auth.currentUser?.email
                ?: return Result.failure(Exception("User tidak terautentikasi"))

            val pickupId = db.reference.child("pickups").push().key
                ?: return Result.failure(Exception("Gagal mendapatkan ID"))

            val pickupData = pickupDomain.toPickupData().copy(email = userEmail) // Tambahkan email ke data

            db.reference.child("pickups").child(pickupId)
                .setValue(pickupData) // Simpan ke Firebase
                .await()

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}