package com.example.wastebank

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.data.repository.AuthRepositoryImpl
import com.example.wastebank.domain.usecase.AuthUseCase
import com.example.wastebank.presentation.ui.screen.*
import com.example.wastebank.presentation.ui.theme.WasteBankTheme
import com.example.wastebank.presentation.viewmodel.AuthViewModel
import com.example.wastebank.ui.splash.RegisterScreen

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WasteBankTheme {
                val navController = rememberNavController()

                // Membuat ViewModel di MainActivity agar bisa diteruskan ke screen lain
                val authRepo = AuthRepositoryImpl()
                val authUseCase = AuthUseCase(authRepo)
                val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(authUseCase))

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "splash_screen"
                    ) {
                        composable("splash_screen") {
                            SplashScreen(navController)
                        }
                        composable("admin_login_screen") {
                            AdminLoginScreen(navController)
                        }
                        composable("user_login_screen") {
                            UserLoginScreen(navController, authViewModel)
                        }
                        composable("register_screen") {
                            RegisterScreen(navController, authViewModel)
                        }
                        composable("forgot_password_screen") {
                            ForgotPasswordScreen(navController, authViewModel)
                        }
                        composable("set_new_password_screen") {
                            SetNewPasswordScreen(navController)
                        }
                        composable("home_screen") {
                            HomeScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
