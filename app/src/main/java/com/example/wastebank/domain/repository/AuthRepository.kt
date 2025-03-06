package com.example.wastebank.domain.repository

interface AuthRepository {
    fun registerUser(email: String, password: String, onResult: (Boolean, String?) -> Unit)
    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit)
    fun logoutUser()
}