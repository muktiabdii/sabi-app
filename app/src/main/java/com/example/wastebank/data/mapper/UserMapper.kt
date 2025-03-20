package com.example.wastebank.data.mapper

import com.example.wastebank.data.model.UserData
import com.example.wastebank.domain.model.UserDomain
import com.google.firebase.database.DataSnapshot

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

    fun mapToData(userDomain: UserDomain): UserData {
        return UserData(
            name = userDomain.name,
            email = userDomain.email,
            phoneNumber = userDomain.phoneNumber,
            gender = userDomain.gender,
            points = userDomain.points
        )
    }

    fun fromSnapshot(snapshot: DataSnapshot): UserData? {
        return snapshot.getValue(UserData::class.java)
    }
}
