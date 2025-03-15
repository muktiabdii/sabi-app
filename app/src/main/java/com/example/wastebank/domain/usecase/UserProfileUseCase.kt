package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileUseCase(private val userProfilerepository: UserProfileRepository) {
    fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit) {
        userProfilerepository.getUserProfile(onResult)
    }

    fun getUserPoint(onResult: (Int?) -> Unit) {
        userProfilerepository.getUserPoint(onResult)
    }

    fun getUserName(onResult: (String?) -> Unit) {
        userProfilerepository.getUserName(onResult)
    }

    fun deleteAccount(onResult: (Boolean, String?) -> Unit) {
        userProfilerepository.deleteAccount(onResult)
    }

}