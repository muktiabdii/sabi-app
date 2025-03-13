package com.example.wastebank.data.model

// Model data pengguna
data class UserModel(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val gender: String = "",
    val points: Int = 0
)
