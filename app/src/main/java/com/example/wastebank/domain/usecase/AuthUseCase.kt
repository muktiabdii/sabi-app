package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {
    fun registerUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        authRepository.registerUser(email, password, onResult)
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        authRepository.loginUser(email, password, onResult)
    }

    fun logoutUser() {
        authRepository.logoutUser()
    }
}