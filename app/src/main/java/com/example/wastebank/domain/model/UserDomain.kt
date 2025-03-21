package com.example.wastebank.domain.model

data class UserDomain(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val gender: String = "",
    val points: Int = 0
)
