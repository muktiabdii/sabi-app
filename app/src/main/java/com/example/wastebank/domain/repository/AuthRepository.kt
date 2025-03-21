package com.example.wastebank.domain.repository

// Interface untuk AuthRepositoryImpl
interface AuthRepository {
    fun registerUser(name: String, email: String, password: String, phoneNumber: String, gender: String, onResult: (Boolean, String?) -> Unit)
    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit)
    fun loginAdmin(email: String, password: String, adminId: String, onResult: (Boolean, String?) -> Unit)
    fun checkPassword(password: String, onResult: (Boolean) -> Unit)
    fun logoutUser()
    fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit)
}