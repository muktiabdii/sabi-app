package com.example.wastebank.data.repository

import android.util.Log
import com.example.wastebank.data.model.UserData
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.repository.AuthRepository

// kelas implementasi dari AuthRepository
class AuthRepositoryImpl : AuthRepository {

    // inisialisasi Firebase Auth dan Firebase Realtime Database
    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    // fungsi untuk melakukan registrasi pengguna
    override fun registerUser(name: String, email: String, password: String, phoneNumber: String, gender: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val userRef = db.getReference("users").child(userId)

                        // menyimpan data pengguna ke Firebase Database menggunakan UserModel
                        val userData = UserData(userId, name, email, phoneNumber, gender)
                        userRef.setValue(userData)
                            .addOnSuccessListener {
                                onResult(true, null)
                            }
                            .addOnFailureListener { exception ->

                                // jika gagal menyimpan ke database, hapus akun dari Authentication
                                user.delete().addOnCompleteListener {
                                    onResult(false, "Gagal menyimpan data user, coba lagi nanti.")
                                }
                            }
                    }
                }

                // jika registrasi gagal, kirim pesan error
                else {
                    val errorMessage = task.exception?.message
                    val localizedMessage = getLocalizedErrorMessage(errorMessage)
                    onResult(false, localizedMessage)
                }
            }
    }



    // fungsi untuk login pengguna
    override fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                }

                // jika login gagal, kirim pesan error
                else {
                    val errorMessage = task.exception?.message
                    Log.e("LoginError", "Login gagal: $errorMessage") // Logging error ke Logcat
                    val localizedMessage = getLocalizedErrorMessage(errorMessage)
                    onResult(false, localizedMessage)
                }
            }
    }


    // fungsi untuk login admin
    override fun loginAdmin(email: String, password: String, adminId: String, onResult: (Boolean, String?) -> Unit) {
        val adminRef = db.getReference("admins").child(adminId)

        // memeriksa apakah adminId ada di Firebase Database
        adminRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val storedEmail = snapshot.child("email").value as? String
                if (storedEmail == email) {

                    // jika adminId sesuai, lakukan login
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onResult(true, null)
                            }

                            // jika login gagal, kirim pesan error
                            else {
                                val errorMessage = task.exception?.message
                                val localizedMessage = getLocalizedErrorMessage(errorMessage)
                                onResult(false, localizedMessage)
                            }
                        }
                }

                // jika adminId tidak sesuai, kirim pesan error
                else {
                    onResult(false, "ID dan email tidak cocok.")
                }
            }

            // jika adminId tidak ditemukan, kirim pesan error
            else {
                onResult(false, "Admin ID tidak ditemukan.")
            }
        }.addOnFailureListener { exception ->
            onResult(false, exception.message)
        }
    }

    // fungsi untuk memeriksa password user
    override fun checkPassword(password: String, onResult: (Boolean) -> Unit) {
        val email = auth.currentUser?.email
        if (email != null) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    onResult(task.isSuccessful)
                }
        }

        // jika email tidak ditemukan, kirimkan false
        else {
            onResult(false)
        }
    }

    // fungsi untuk melakukan logout pengguna
    override fun logoutUser() {
        auth.signOut()
    }

    // fungsi untuk melakukan reset password
    override fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                }

                // jika reset password gagal, kirim pesan error
                else {
                    onResult(false, "Gagal mengirim kode reset password")
                }
            }
    }

    // fungsi untuk mendapatkan pesan error yang sesuai
    private fun getLocalizedErrorMessage(errorMessage: String?): String {
        return when {
            errorMessage?.contains("The email address is badly formatted") == true ->
                "Format email salah"

            errorMessage?.contains("The supplied auth credential is incorrect, malformed or has expired.") == true ->
                "Silahkan periksa kembali email dan password Anda"

            errorMessage?.contains("A network error") == true ->
                "Terjadi kesalahan jaringan"

            else -> errorMessage ?: "Terjadi kesalahan saat login"
        }
    }
}