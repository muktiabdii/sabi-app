package com.example.wastebank.domain.repository

interface UserProfileRepository {
    fun getUserPoint(onResult: (Int?) -> Unit)
    fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit)
}