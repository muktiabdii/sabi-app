package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.UserMapper
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileRepositoryImpl : UserProfileRepository{

    // Inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db
    private val usreRef = db.getReference("users")

    override fun getUserPoin(onResult: (Int?) -> Unit) {

        // Ambil userId dari user yang sedang login
        val userId = auth.currentUser?.uid
        if (userId != null) {
            usreRef.child(userId).get().addOnSuccessListener { snapshot ->
                val user = UserMapper.mapToDomain(snapshot)

                // Mengambil data point user
                onResult(user?.points)
            }.addOnFailureListener {
                onResult(null)
            }
        }

        else {
            onResult(null)
        }
    }
}