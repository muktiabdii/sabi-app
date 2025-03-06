package com.example.wastebank.data.repository

import android.util.Log
import com.example.wastebank.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase

// Implementasi dari AuthRepository
class AuthRepositoryImpl : AuthRepository {

    // Inisialisasi FirebaseAuth
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Inisialisasi Firebase Realtime Database
    val db = FirebaseDatabase.getInstance("https://waste-bank-3db5d-default-rtdb.asia-southeast1.firebasedatabase.app")

    // Fungsi untuk melakukan registrasi pengguna
    override fun registerUser(email: String, password: String, phoneNumber: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val userRef = db.getReference("users").child(userId)

                        val userData = mapOf(
                            "email" to email,
                            "phoneNumber" to phoneNumber
                        )

                        userRef.setValue(userData)
                            .addOnSuccessListener {
                                onResult(true, null)
                            }
                            .addOnFailureListener { exception ->
                                onResult(false, exception.message)
                            }
                    }
                }

                else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    // Fungsi untuk melakukan login pengguna
    override fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                }

                else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    // Fungsi untuk melakukan logout pengguna
    override fun logoutUser() {
        auth.signOut()
    }
}