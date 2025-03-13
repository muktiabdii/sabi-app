package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.UserMapper
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileRepositoryImpl : UserProfileRepository{

    // Inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db
    private val userRef = db.getReference("users")

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

    override fun getUserName(onResult: (String?) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            userRef.child(userId).child("name").get().addOnSuccessListener { snapshot ->
                val name = snapshot.getValue(String::class.java) ?: ""
                println("Nama yang didapat dari Firebase: $name") // Debugging
                onResult(name)
            }.addOnFailureListener {
                println("Gagal mengambil nama dari Firebase") // Debugging
                onResult("")
            }
        } else {
            println("User tidak login") // Debugging
            onResult("")
        }
    }


    override fun getUserPoint(onResult: (Int?) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            userRef.child(userId).child("points").get().addOnSuccessListener { snapshot ->
                val points = snapshot.getValue(Int::class.java) ?: 0
                onResult(points)
            }.addOnFailureListener {
                onResult(0)
            }
        }

        else {
            onResult(0)
        }
    }

}