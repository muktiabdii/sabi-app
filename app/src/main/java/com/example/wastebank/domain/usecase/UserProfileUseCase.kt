package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileUseCase(private val repository: UserProfileRepository) {
    fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit) {
        repository.getUserProfile(onResult)
    }

    fun getUserPoint(onResult: (Int?) -> Unit) {
        repository.getUserPoint(onResult)
    }

    fun getUserName(onResult: (String?) -> Unit) {
        repository.getUserName(onResult)
    }

}