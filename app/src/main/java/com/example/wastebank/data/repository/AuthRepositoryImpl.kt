package com.example.wastebank.data.repository

import com.example.wastebank.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

// Implementasi dari AuthRepository
class AuthRepositoryImpl : AuthRepository {

    // Inisialisasi FirebaseAuth
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Fungsi untuk melakukan registrasi pengguna
    override fun registerUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
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