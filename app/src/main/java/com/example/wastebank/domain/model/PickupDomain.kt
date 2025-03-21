package com.example.wastebank.domain.model

data class PickupDomain(
    val email: String = "",
    val address: String = "",
    val weight: String = "",
    val phoneNumber: String = "",
    val selectedDate: String = "",
    val trashType: String = "",
    val selectedTime: String = "",
    val description: String = "",
    val image: String = ""
)
