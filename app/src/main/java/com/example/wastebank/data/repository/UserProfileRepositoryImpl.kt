package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.UserMapper
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.UserProfileRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class UserProfileRepositoryImpl : UserProfileRepository{

    // Inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db
    private val userRef = db.getReference("users")

    override suspend fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit) {

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

    override suspend fun editUserProfile(name: String, phoneNumber: String, email: String, password: String, gender: String, onResult: (Boolean, String?) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            userRef.child(userId).child("name").setValue(name)
            userRef.child(userId).child("phoneNumber").setValue(phoneNumber)
            userRef.child(userId).child("email").setValue(email)
            userRef.child(userId).child("password").setValue(password)
            userRef.child(userId).child("gender").setValue(gender)
            onResult(true, null)
        } else {
            onResult(false, "User not authenticated")
        }
    }

    override suspend fun getUserName(onResult: (String?) -> Unit) {
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


    override suspend fun getUserPoint(onResult: (Int?) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            userRef.child(userId).child("points").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val point = snapshot.getValue(Int::class.java) ?: 0
                    onResult(point)
                }

                override fun onCancelled(error: DatabaseError) {
                    onResult(null)
                }
            })
        } else {
            onResult(null)
        }
    }

    override suspend fun deleteAccount(onResult: (Boolean, String?) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            try {
                userRef.child(userId).removeValue().addOnSuccessListener {
                    // Logout setelah penghapusan akun
                    auth.signOut()
                    onResult(true, null)
                }.addOnFailureListener { exception ->
                    // Memberikan pesan error yang lebih spesifik
                    onResult(false, exception.localizedMessage)
                }
            } catch (e: Exception) {
                // Menangani kesalahan umum yang mungkin terjadi
                onResult(false, e.localizedMessage)
            }
        } else {
            onResult(false, "User not authenticated")
        }
    }

}