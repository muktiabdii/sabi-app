package com.example.wastebank.data.repository

import com.example.wastebank.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl : AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

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

    override fun logoutUser() {
        auth.signOut()
    }
}