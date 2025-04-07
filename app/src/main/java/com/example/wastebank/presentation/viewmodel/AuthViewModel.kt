package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wastebank.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    private val _adminId = MutableStateFlow("")
    val adminId: StateFlow<String> = _adminId

    private val _role = MutableStateFlow<String?>(null)
    val role: StateFlow<String?> = _role

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _isResetPassword = MutableStateFlow(false)
    val isResetPassword: StateFlow<Boolean> = _isResetPassword

    // Fungsi untuk memperbarui data pengguna
    fun updateName(value: String) { _name.value = value }
    fun updateEmail(value: String) { _email.value = value }
    fun updatePassword(value: String) { _password.value = value }
    fun updatePhoneNumber(value: String) { _phoneNumber.value = value }
    fun updateGender(value: String) { _gender.value = value }
    fun updateAdminId(value: String) { _adminId.value = value }

    // fungsi untuk reset data pengguna
    fun clearNameInput() { _name.value = "" }
    fun clearEmailInput() { _email.value = "" }
    fun clearPhoneNumberInput() { _phoneNumber.value = "" }
    fun clearPasswordInput() { _password.value = "" }
    fun clearGenderInput() { _gender.value = "" }
    fun clearAdminIdInput() { _adminId.value = "" }

    // Fungsi untuk mereset status registrasi
    fun resetErrorMessage() { _errorMessage.value = null }
    fun resetIsRegistered() { _isRegistered.value = false }
    fun resetIsLoggedIn() { _isLoggedIn.value = false }
    fun resetIsResetPassword() { _isResetPassword.value = false }

    fun register() {
        viewModelScope.launch {
            authUseCase.registerUser(
                name.value, email.value, password.value, phoneNumber.value, gender.value
            ) { success, message ->
                if (success) {
                    _isRegistered.value = true
                }

                else {
                    _errorMessage.value = message
                }
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            authUseCase.loginUser(
                email.value, password.value
            ) { success, message ->
                if (success) {
                    _isLoggedIn.value = true
                    _role.value = "user"
                }

                else {
                    _errorMessage.value = message
                }
            }
        }
    }

    fun loginAdmin() {
        viewModelScope.launch {
            authUseCase.loginAdmin(
                email.value, password.value, adminId.value
            ) { success, message ->
                if (success) {
                    _isLoggedIn.value = true
                    _role.value = "admin"
                }

                else {
                    _errorMessage.value = message
                }
            }
        }
    }

    fun checkPassword(onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            authUseCase.checkPassword(password.value) { success ->
                if (success) {
                    onResult(true, "")
                } else {
                    onResult(false, "")
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authUseCase.logoutUser()
            _isLoggedIn.value = false
            _role.value = null
        }
    }

    fun resetPassword() {
        viewModelScope.launch {
            authUseCase.resetPassword(
                email.value
            ) { success, message ->
                if (success) {
                    _isResetPassword.value = true
                }

                else {
                    _errorMessage.value = message
                }
            }
        }
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