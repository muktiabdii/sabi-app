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

    // Fungsi untuk mereset status registrasi
    fun clearPasswordInput() { _password.value = "" }
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
                    _isRegistered.value = true // Simpan status registrasi ke state
                }

                else {
                    _errorMessage.value = message // Simpan message error ke state
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
                    _isLoggedIn.value = true // Simpan status login ke state
                }

                else {
                    _errorMessage.value = message // Simpan message error ke state
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
                    _isLoggedIn.value = true // Simpan status login ke state
                }

                else {
                    _errorMessage.value = message // Simpan message error ke state
                }
            }
        }
    }


    fun checkPassword(onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            authUseCase.checkPassword(password.value) { success ->
                if (success) {
                    onResult(true, "") // Kirim hasil sukses dengan pesan kosong
                } else {
                    onResult(false, "") // Kirim hasil gagal dengan pesan error
                }
            }
        }
    }



    fun logout() {
        viewModelScope.launch {
            authUseCase.logoutUser() // Panggil fungsi logoutUser dari AuthUseCase
        }
    }



    fun resetPassword() {
        viewModelScope.launch {
            authUseCase.resetPassword(
                email.value
            ) { success, message ->
                if (success) {
                    _isResetPassword.value = true // Simpan status reset password ke state
                }

                else {
                    _errorMessage.value = message // Simpan message error ke state
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