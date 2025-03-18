package com.example.wastebank.domain.repository

interface UserProfileRepository {
    suspend fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit)
    suspend fun editUserProfile(name: String, phoneNumber: String, email: String, password: String, gender: String, onResult: (Boolean, String?) -> Unit)
    suspend fun getUserPoint(onResult: (Int?) -> Unit)
    suspend fun getUserName(onResult: (String?) -> Unit)
    suspend fun deleteAccount(onResult: (Boolean, String?) -> Unit)
}