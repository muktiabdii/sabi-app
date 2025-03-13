package com.example.wastebank.data.mapper

import com.example.wastebank.data.model.UserModel
import com.google.firebase.database.DataSnapshot

object UserMapper {
    fun mapToDomain(snapshot: DataSnapshot): UserModel? {
        return snapshot.getValue(UserModel::class.java)
    }
}