package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wastebank.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow

// ViewModel untuk Auth
class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    // State untuk menyimpan data pengguna
    var name = MutableStateFlow("")
    var email = MutableStateFlow("")
    var password = MutableStateFlow("")
    var phoneNumber = MutableStateFlow("")
    var gender = MutableStateFlow("")

    // Fungsi untuk memperbarui data pengguna
    fun updateName(value: String) {
        name.value = value
    }

    fun updateEmail(value: String) {
        email.value = value
    }

    fun updatePassword(value: String) {
        password.value = value
    }

    fun updatePhoneNumber(value: String) {
        phoneNumber.value = value
    }

    fun updateGender(value: String) {
        gender.value = value
    }

    fun register(onResult: (Boolean, String?) -> Unit) {
        authUseCase.registerUser(name.value, email.value, password.value, phoneNumber.value, gender.value, onResult) // Panggil fungsi registerUser dari AuthUseCase
    }

    fun login(onResult: (Boolean, String?) -> Unit) {
        authUseCase.loginUser(email.value, password.value, onResult) // Panggil fungsi loginUser dari AuthUseCase
    }

    fun logout() {
        authUseCase.logoutUser() // Panggil fungsi logoutUser dari AuthUseCase
    }

    fun resetPassword(onResult: (Boolean, String?) -> Unit) {
        authUseCase.resetPassword(email.value, onResult) // Panggil fungsi resetPassword dari AuthUseCase
    }
}