package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileUseCase(private val userProfilerepository: UserProfileRepository) {
    suspend fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit) {
        userProfilerepository.getUserProfile(onResult)
    }

    suspend fun editUserProfile(name: String, phoneNumber: String, email: String, password: String, gender: String, onResult: (Boolean, String?) -> Unit) {
        userProfilerepository.editUserProfile(name, phoneNumber, email, password, gender, onResult)
    }

    suspend fun getUserPoint(onResult: (Int?) -> Unit) {
        userProfilerepository.getUserPoint(onResult)
    }

    suspend fun getUserName(onResult: (String?) -> Unit) {
        userProfilerepository.getUserName(onResult)
    }

    suspend fun deleteAccount(onResult: (Boolean, String?) -> Unit) {
        userProfilerepository.deleteAccount(onResult)
    }

}