package com.example.wastebank

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.data.repository.AuthRepositoryImpl
import com.example.wastebank.domain.usecase.AuthUseCase
import com.example.wastebank.presentation.ui.screen.AdminLoginScreen
import com.example.wastebank.presentation.ui.screen.ForgotPasswordScreen
import com.example.wastebank.presentation.ui.screen.HomeScreen
import com.example.wastebank.presentation.ui.screen.SetNewPasswordScreen
import com.example.wastebank.presentation.ui.screen.SplashScreen
import com.example.wastebank.presentation.ui.screen.UserLoginScreen
import com.example.wastebank.presentation.ui.theme.WasteBankTheme
import com.example.wastebank.presentation.viewmodel.AuthViewModel
import com.example.wastebank.ui.splash.RegisterScreen

class MainActivity : ComponentActivity() {

    // Membuat instance AuthRepositoryImpl untuk digunakan dalam AuthUseCase
    private val authRepo = AuthRepositoryImpl()

    // Membuat instance AuthUseCase dengan AuthRepositoryImpl
    private val authUseCase = AuthUseCase(authRepo)

    // Membuat instance AuthViewModel dengan AuthUseCase
    private val authViewModel = AuthViewModel(authUseCase)

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

//                set pengatur navigasi
                val navController = rememberNavController()
//                val botNavViewModel = BotNavViewModel()
//                val homeViewModel: HomeViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash_screen"
                    ) {
                        composable(route = "splash_screen") {
                            SplashScreen(navController)
                        }
                        composable(route = "admin_login_screen") {
                            AdminLoginScreen(navController)
                        }
                        composable(route = "user_login_screen") {
                            UserLoginScreen(navController)
                        }
                        composable(route = "register_screen") {
                            RegisterScreen(navController)
                        }
                        composable(route = "forgot_password_screen") {
                            ForgotPasswordScreen(navController)
                        }
                        composable(route = "set_new_password_screen") {
                            SetNewPasswordScreen(navController)
                        }
                        composable(route = "home_screen") {
                            HomeScreen(navController)
                        }
                    }
                }
            }
        }
    }
}