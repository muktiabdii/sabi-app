package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {
    fun registerUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (!validateEmail(email)) {
            onResult(false, "Email tidak valid")
            return
        }

        if (!validatePass(password)) {
            onResult(false, "Password harus terdiri dari minimal 6 karakter dan mengandung angka")
            return
        }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        authRepository.loginUser(email, password, onResult)
    }

    fun logoutUser() {
        authRepository.logoutUser()
    }

    private fun validateEmail(email: String): Boolean {
        return email.contains("@")
    }

    private fun validatePass(password: String): Boolean {
        return password.length >= 6 && password.any { it.isDigit() }
    }
}