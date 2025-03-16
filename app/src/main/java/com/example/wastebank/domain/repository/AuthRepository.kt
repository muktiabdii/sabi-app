package com.example.wastebank.domain.repository

// Interface untuk AuthRepositoryImpl
interface AuthRepository {
    suspend fun registerUser(name: String, email: String, password: String, phoneNumber: String, gender: String, onResult: (Boolean, String?) -> Unit)
    suspend fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit)
    suspend fun loginAdmin(email: String, password: String, adminId: String, onResult: (Boolean, String?) -> Unit)
    suspend fun checkPassword(password: String, onResult: (Boolean) -> Unit)
    suspend fun logoutUser()
    suspend fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit)
}