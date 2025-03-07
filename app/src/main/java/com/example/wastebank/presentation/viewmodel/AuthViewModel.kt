package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wastebank.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// ViewModel untuk Auth
class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    // State untuk menyimpan data pengguna
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> = _gender

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Fungsi untuk memperbarui data pengguna
    fun updateName(value: String) { _name.value = value }
    fun updateEmail(value: String) { _email.value = value }
    fun updatePassword(value: String) { _password.value = value }
    fun updatePhoneNumber(value: String) { _phoneNumber.value = value }
    fun updateGender(value: String) { _gender.value = value }

    fun register() {
        authUseCase.registerUser(
            name.value, email.value, password.value, phoneNumber.value, gender.value
        ) { success, message ->
            if (!success) {
                _errorMessage.value = message // Simpan message error ke state
            }
        }
    }

    fun login() {
        authUseCase.loginUser(
            email.value, password.value
        ) { success, message ->
            if (!success) {
                _errorMessage.value = message // Simpan message error ke state
            }
        }
    }

    fun logout() {
        authUseCase.logoutUser() // Panggil fungsi logoutUser dari AuthUseCase
    }

    fun resetPassword(onResult: (Boolean, String?) -> Unit) {
        authUseCase.resetPassword(email.value, onResult) // Panggil fungsi resetPassword dari AuthUseCase
    }

    class Factory(private val authUseCase: AuthUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(authUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}