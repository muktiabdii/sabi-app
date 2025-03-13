package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileUseCase(private val repository: UserProfileRepository) {
    fun getUserPoint(onResult: (Int?) -> Unit) {
        repository.getUserPoint(onResult)
    }

    fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit) {
        repository.getUserProfile(onResult)
    }
}