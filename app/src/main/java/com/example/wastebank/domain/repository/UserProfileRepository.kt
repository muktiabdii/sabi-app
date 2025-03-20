package com.example.wastebank.domain.repository

import com.example.wastebank.domain.model.UserDomain

interface UserProfileRepository {
    suspend fun getUserProfile(): UserDomain?
    suspend fun editUserProfile(user: UserDomain): Result<Unit>
    suspend fun getUserPoint(): Int?
    suspend fun getUserName(): String?
    suspend fun deleteAccount(): Result<Unit>
}
