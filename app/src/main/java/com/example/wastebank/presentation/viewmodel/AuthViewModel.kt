package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wastebank.domain.usecase.AuthUseCase

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {
    fun register(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        authUseCase.registerUser(email, password, onResult)
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        authUseCase.loginUser(email, password, onResult)
    }

    fun logout() {
        authUseCase.logoutUser()
    }
}