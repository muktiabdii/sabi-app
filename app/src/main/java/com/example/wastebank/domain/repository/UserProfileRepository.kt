package com.example.wastebank.domain.repository

interface UserProfileRepository {
    fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit)
    fun getUserPoint(onResult: (Int?) -> Unit)
    fun getUserName(onResult: (String?) -> Unit)
}