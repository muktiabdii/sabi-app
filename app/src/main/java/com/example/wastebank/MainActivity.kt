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

    private val authRepo = AuthRepositoryImpl()
    private val authUseCase = AuthUseCase(authRepo)
    private val authViewModel = AuthViewModel(authUseCase)
    private lateinit var firebaseApp: FirebaseApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseApp = FirebaseApp.getInstance()

        setContent {
            WasteBankTheme {
                LaunchedEffect(Unit) {
                    authViewModel.register("abdi@example.com", "password217") { success, message ->
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
