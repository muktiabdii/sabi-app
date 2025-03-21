package com.example.wastebank.data.mapper

import com.example.wastebank.data.model.UserData
import com.example.wastebank.domain.model.UserDomain
import com.google.firebase.database.DataSnapshot

// mapper untuk mengonversi data UserData ke UserDomain
object UserMapper {
    fun mapToDomain(userData: UserData): UserDomain {
        return UserDomain(
            name = userData.name,
            email = userData.email,
            phoneNumber = userData.phoneNumber,
            gender = userData.gender,
            points = userData.points
        )
    }
}
