package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.UserMapper
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileRepositoryImpl : UserProfileRepository{

    // Inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db
    private val userRef = db.getReference("users")

    override fun getUserPoint(onResult: (Int?) -> Unit) {

        // Ambil userId dari user yang sedang login
        val userId = auth.currentUser?.uid
        if (userId != null) {
            userRef.child(userId).get().addOnSuccessListener { snapshot ->
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

    override fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit) {

        // Ambil userId dari user yang sedang login
        val userId = auth.currentUser?.uid
        if (userId != null) {
            userRef.child(userId).get().addOnSuccessListener { snapshot ->
                val user = UserMapper.mapToDomain(snapshot)

                // Mengambil data user
                onResult(user?.name, user?.email, user?.phoneNumber, user?.gender, user?.points)
            }.addOnFailureListener {
                onResult(null, null, null, null, null)
            }
        }

        else{
            onResult(null, null, null, null, null)
        }
    }
}