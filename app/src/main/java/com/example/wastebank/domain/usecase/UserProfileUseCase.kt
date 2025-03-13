package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileUseCase(private val repository: UserProfileRepository) {
    fun getUserPoin(onResult: (Int?) -> Unit) {
        repository.getUserPoin(onResult)
    }
}