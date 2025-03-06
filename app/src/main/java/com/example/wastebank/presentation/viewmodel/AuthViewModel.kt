package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wastebank.domain.usecase.AuthUseCase

// ViewModel untuk Auth
class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {
    fun register(email: String, password: String, phoneNumber: String, onResult: (Boolean, String?) -> Unit) {
        authUseCase.registerUser(email, password, phoneNumber, onResult) // Panggil fungsi registerUser dari AuthUseCase
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        authUseCase.loginUser(email, password, onResult) // Panggil fungsi loginUser dari AuthUseCase
    }

    fun logout() {
        authUseCase.logoutUser() // Panggil fungsi logoutUser dari AuthUseCase
    }
}