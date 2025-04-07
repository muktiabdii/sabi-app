package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.AuthRepository

// Kelas AuthUseCase yang digunakan untuk melakukan validasi dan autentikasi pengguna
class AuthUseCase(private val authRepository: AuthRepository) {
    fun registerUser(name: String, email: String, password: String, phoneNumber: String, gender: String, onResult: (Boolean, String?) -> Unit) {

        // Validasi input
        if (name.isBlank() || email.isBlank() || password.isBlank() || phoneNumber.isBlank() || gender.isBlank()) {
            onResult(false, "Harap isi semua data sebelum mendaftar")
            return
        }

        // Validasi email
        if (!validateEmail(email)) {
            onResult(false, "Email tidak valid")
            return
        }

        // Validasi password
        if (!validatePass(password)) {
            onResult(false, "Password harus terdiri dari minimal 8 karakter dan mengandung angka")
            return
        }

        // Validasi nomor hp
        if (!validateNumber(phoneNumber)) {
            onResult(false, "Nomor HP harus terdiri dari minimal 8 karakter")
            return
        }

        authRepository.registerUser(name, email, password, phoneNumber, gender, onResult)
    }


    fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {

        // Validasi input
        if (email.isBlank() || password.isBlank()) {
            onResult(false, "Harap isi semua data sebelum masuk")
            return
        }

        authRepository.loginUser(email, password, onResult)
    }


    fun loginAdmin(email: String, password: String, adminId: String, onResult: (Boolean, String?) -> Unit) {

        // Validasi input
        if (email.isBlank() || password.isBlank() || adminId.isBlank()) {
            onResult(false, "Harap isi semua data sebelum masuk")
            return
        }

        authRepository.loginAdmin(email, password, adminId, onResult)
    }

    fun checkPassword(password: String, onResult: (Boolean) -> Unit) {
        authRepository.checkPassword(password, onResult)
    }

    fun logoutUser() {
        authRepository.logoutUser()
    }


    fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        authRepository.resetPassword(email, onResult)
    }


    // Fungsi untuk melakukan validasi email
    private fun validateEmail(email: String): Boolean {
        return email.contains("@")
    }


    // Fungsi untuk melakukan validasi password
    private fun validatePass(password: String): Boolean {
        return password.length >= 8 && password.any { it.isDigit() } && password.any { it.isLetter() }
    }


    // Fungsi untuk melakukan validasi nomor HP
    private fun validateNumber(number: String): Boolean {
        return number.length >= 8
    }
}