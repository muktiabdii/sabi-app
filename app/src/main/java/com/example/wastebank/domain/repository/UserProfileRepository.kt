package com.example.wastebank.domain.repository

interface UserProfileRepository {
    fun getUserPoin(onResult: (Int?) -> Unit)
}