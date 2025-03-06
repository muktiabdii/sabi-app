package com.example.wastebank.data.repository

import com.example.wastebank.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

// Implementasi dari AuthRepository
class AuthRepositoryImpl : AuthRepository {

    // Inisialisasi FirebaseAuth
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Inisialisasi Firebase Realtime Database
    private val db = FirebaseDatabase.getInstance("https://waste-bank-3db5d-default-rtdb.asia-southeast1.firebasedatabase.app")

    // Fungsi untuk melakukan registrasi pengguna
    override fun registerUser(nama: String, email: String, password: String, phoneNumber: String, gender: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val userRef = db.getReference("users").child(userId)

                        // Menyimpan data pengguna ke Firebase Database
                        val userData = mapOf(
                            "nama" to nama,
                            "email" to email,
                            "phoneNumber" to phoneNumber,
                            "gender" to gender
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

    // Fungsi untuk melakukan reset password
    override fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
    }
}