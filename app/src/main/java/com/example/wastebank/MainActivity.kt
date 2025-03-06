package com.example.wastebank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.example.wastebank.data.repository.AuthRepositoryImpl
import com.example.wastebank.domain.usecase.AuthUseCase
import com.example.wastebank.presentation.ui.theme.WasteBankTheme
import com.example.wastebank.presentation.viewmodel.AuthViewModel

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

//                    authViewModel.register("mukti", "abdisyukur10@gmail.com", "123456", "08123456789", "Pria") { success, message ->
//                        if (success) {
//                            // Registrasi berhasil
//                            println("Registrasi berhasil")
//                        } else {
//                            // Registrasi gagal
//                            println("Registrasi gagal: $message")
//                        }
//                    }

                    authViewModel.resetPassword("abdisyukur10@gmail.com") { success, message ->
                        if (success) {
                            // Password berhasil diubah
                            println("Password berhasil diubah")
                        } else {
                            // Password gagal diubah
                            println("Password gagal diubah: $message")
                        }
                    }
                }
            }
        }
    }
}