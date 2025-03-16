package com.example.wastebank.domain.repository

interface UserProfileRepository {
    fun getUserProfile(onResult: (String?, String?, String?, String?, Int?) -> Unit)
    fun editUserProfile(name: String, phoneNumber: String, email: String, password: String, gender: String, onResult: (Boolean, String?) -> Unit)
    fun getUserPoint(onResult: (Int?) -> Unit)
    fun getUserName(onResult: (String?) -> Unit)
    fun deleteAccount(onResult: (Boolean, String?) -> Unit)
}