package com.example.wastebank.data.model

// Model data pengguna
data class UserData(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val gender: String = "",
    val points: Int = 0
)
