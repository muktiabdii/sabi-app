package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.model.UserDomain
import com.example.wastebank.domain.repository.UserProfileRepository

class UserProfileUseCase(private val userProfileRepository: UserProfileRepository) {

    suspend fun getUserProfile(): UserDomain? {
        return userProfileRepository.getUserProfile()
    }

    suspend fun editUserProfile(user: UserDomain): Result<Unit> {
        return userProfileRepository.editUserProfile(user)
    }

    suspend fun getUserPoint(): Int? {
        return userProfileRepository.getUserPoint()
    }

    suspend fun getUserName(): String? {
        return userProfileRepository.getUserName()
    }

    suspend fun deleteAccount(): Result<Unit> {
        return userProfileRepository.deleteAccount()
    }
}