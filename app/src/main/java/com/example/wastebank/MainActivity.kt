package com.example.wastebank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.example.wastebank.data.repository.AuthRepositoryImpl
import com.example.wastebank.domain.usecase.AuthUseCase
import com.example.wastebank.presentation.ui.theme.WasteBankTheme
import com.example.wastebank.presentation.viewmodel.AuthViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    // Membuat instance AuthRepositoryImpl untuk digunakan dalam AuthUseCase
    private val authRepo = AuthRepositoryImpl()

    // Membuat instance AuthUseCase dengan AuthRepositoryImpl
    private val authUseCase = AuthUseCase(authRepo)

    // Membuat instance AuthViewModel dengan AuthUseCase
    private val authViewModel = AuthViewModel(authUseCase)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WasteBankTheme {
                LaunchedEffect(Unit) {

                    // Contoh penggunaan AuthUseCase untuk login
                    authViewModel.register("abdi@example.com", "password217", "089653111625") { success, message ->
                        if (success) {
                            println("✅ Registrasi berhasil!")
                        }

                        else {
                            println("❌ Gagal: $message")
                        }
                    }
                }
            }
        }
    }
}
