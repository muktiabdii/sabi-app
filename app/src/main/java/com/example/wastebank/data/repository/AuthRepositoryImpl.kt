package com.example.wastebank.data.repository

import android.util.Log
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.AuthRepository

// Implementasi dari AuthRepository
class AuthRepositoryImpl : AuthRepository {

    // Inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    // Fungsi untuk melakukan registrasi pengguna
    override fun registerUser(name: String, email: String, password: String, phoneNumber: String, gender: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val userRef = db.getReference("users").child(userId)

                        // Menyimpan data pengguna ke Firebase Database
                        val userData = mapOf(
                            "name" to name,
                            "email" to email,
                            "phoneNumber" to phoneNumber,
                            "gender" to gender
                        )

                        userRef.setValue(userData)
                            .addOnSuccessListener {
                                onResult(true, null)
                            }

                            .addOnFailureListener { exception ->
                                onResult(false, null)
                            }
                    }
                }

                // Jika registrasi gagal, kirim pesan error
                else {
                    val errorMessage = task.exception?.message
                    val localizedMessage = getLocalizedErrorMessage(errorMessage)
                    onResult(false, localizedMessage)
                }
            }
    }


    // Fungsi untuk melakukan login pengguna
    override fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                }

                // Jika login gagal, kirim pesan error
                else {
                    val errorMessage = task.exception?.message
                    Log.e("LoginError", "Login gagal: $errorMessage") // Logging error ke Logcat
                    val localizedMessage = getLocalizedErrorMessage(errorMessage)
                    onResult(false, localizedMessage)
                }
            }
    }


    override fun loginAdmin(email: String, password: String, adminId: String, onResult: (Boolean, String?) -> Unit) {
        val adminRef = db.getReference("admins").child(adminId)

        // Memeriksa apakah adminId ada di Firebase Database
        adminRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val storedEmail = snapshot.child("email").value as? String
                if (storedEmail == email) {

                    // Jika adminId sesuai, lakukan login
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onResult(true, null)
                            }

                            // Jika login gagal, kirim pesan error
                            else {
                                val errorMessage = task.exception?.message
                                val localizedMessage = getLocalizedErrorMessage(errorMessage)
                                onResult(false, localizedMessage)
                            }
                        }
                }

                else {
                    onResult(false, "ID dan email tidak cocok.")
                }
            }

            else {
                onResult(false, "Admin ID tidak ditemukan.")
            }
        }.addOnFailureListener { exception ->
            onResult(false, exception.message)
        }
    }


    // Fungsi untuk melakukan logout pengguna
    override fun logoutUser() {
        auth.signOut()
    }


    // Fungsi untuk melakukan reset password
    override fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                }

                // Jika reset password gagal, kirim pesan error
                else {
                    onResult(false, "Gagal mengirim kode reset password")
                }
            }
    }

    // Fungsi untuk mendapatkan pesan error yang sesuai
    private fun getLocalizedErrorMessage(errorMessage: String?): String {
        return when {
            errorMessage?.contains("The email address is badly formatted") == true ->
                "Format email salah"

            errorMessage?.contains("The supplied auth credential is incorrect, malformed or has expired.") == true ->
                "Silahkan periksa kembali email dan password Anda"

            errorMessage?.contains("A network error") == true ->
                "Terjadi kesalahan jaringan"

            else -> "Terjadi kesalahan saat login"
        }
    }
}