package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.AuthRepository

// Kelas AuthUseCase yang digunakan untuk melakukan validasi dan autentikasi pengguna
class AuthUseCase(private val authRepository: AuthRepository) {
    fun registerUser(email: String, password: String, phoneNumber: String, onResult: (Boolean, String?) -> Unit) {

        // Validasi email
        if (!validateEmail(email)) {
            onResult(false, "Email tidak valid")
            return
        }

        // Validasi password
        if (!validatePass(password)) {
            onResult(false, "Password harus terdiri dari minimal 6 karakter dan mengandung angka")
            return
        }

        // Validasi nomor hp
        if (!validateNumber(phoneNumber)) {
            onResult(false, "Nomor HP harus terdiri dari minimal 8 karakter")
            return
        }

        authRepository.registerUser(email, password, phoneNumber, onResult) // Panggil fungsi registerUser dari AuthRepository
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        authRepository.loginUser(email, password, onResult) // Panggil fungsi loginUser dari AuthRepository
    }

    fun logoutUser() {
        authRepository.logoutUser() // Panggil fungsi logoutUser dari AuthRepository
    }

    // Fungsi untuk melakukan validasi email
    private fun validateEmail(email: String): Boolean {
        return email.contains("@")
    }

    // Fungsi untuk melakukan validasi password
    private fun validatePass(password: String): Boolean {
        return password.length >= 6 && password.any { it.isDigit() }
    }

    // Fungsi untuk melakukan validasi nomor HP
    private fun validateNumber(number: String): Boolean {
        return number.length >= 8
    }
}