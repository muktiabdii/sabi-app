package com.example.wastebank

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.donation.presentation.ui.screen.DonateScreen
import com.example.wastebank.data.repository.AuthRepositoryImpl
import com.example.wastebank.data.repository.DonationRepositoryImpl
import com.example.wastebank.data.repository.MoneyExchangeRepositoryImpl
import com.example.wastebank.data.repository.ProductRepositoryImpl
import com.example.wastebank.data.repository.UploadcareRepositoryImpl
import com.example.wastebank.data.repository.UserProfileRepositoryImpl
import com.example.wastebank.domain.usecase.AuthUseCase
import com.example.wastebank.domain.usecase.DonationUseCase
import com.example.wastebank.domain.usecase.MoneyExchangeUseCase
import com.example.wastebank.domain.usecase.ProductUseCase
import com.example.wastebank.domain.usecase.UploadcareUseCase
import com.example.wastebank.domain.usecase.UserProfileUseCase
import com.example.wastebank.presentation.ui.component.BottomNavigation
import com.example.wastebank.presentation.ui.screen.*
import com.example.wastebank.presentation.ui.theme.WasteBankTheme
import com.example.wastebank.presentation.viewmodel.AuthViewModel
import com.example.wastebank.presentation.viewmodel.DonationViewModel
import com.example.wastebank.presentation.viewmodel.MoneyExchangeViewModel
import com.example.wastebank.presentation.viewmodel.ProductViewModel
import com.example.wastebank.presentation.viewmodel.UploadcareViewModel
import com.example.wastebank.presentation.viewmodel.UserProfileViewModel
import com.example.wastebank.ui.splash.RegisterScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WasteBankTheme {
                val navController = rememberNavController()

                // Inisiasi authRepo, authUseCase, dan authViewModel
                val authRepo = AuthRepositoryImpl()
                val authUseCase = AuthUseCase(authRepo)
                val authViewModel: AuthViewModel =
                    viewModel(factory = AuthViewModel.Factory(authUseCase))

                // Inisiasi userProfilRepo, userProfileUseCase, dan userProfileViewModel
                val userProfileRepo = UserProfileRepositoryImpl()
                val userProfileUseCase = UserProfileUseCase(userProfileRepo)
                val userProfileViewModel: UserProfileViewModel = viewModel(factory = UserProfileViewModel.Factory(userProfileUseCase))

                // Inisiasi moneyExchangeRepo, moneyExchangeUseCase, dan moneyExchangeViewModel
                val moneyExchangeRepo = MoneyExchangeRepositoryImpl()
                val moneyExchangeUseCase = MoneyExchangeUseCase(moneyExchangeRepo)
                val moneyExchangeViewModel: MoneyExchangeViewModel = viewModel(factory = MoneyExchangeViewModel.Factory(moneyExchangeUseCase))

                // Inisasi productRepo, productUseCase, dan productViewModel
                val productRepo = ProductRepositoryImpl()
                val productUseCase = ProductUseCase(productRepo)
                val productViewModel: ProductViewModel = viewModel(factory = ProductViewModel.Factory(productUseCase))

                // Inisiasi donationrRepo, donationUseCase, dan donationViewModel
                val donationRepo = DonationRepositoryImpl()
                val donationUseCase = DonationUseCase(donationRepo)
                val donationViewModel: DonationViewModel = viewModel(factory = DonationViewModel.Factory(donationUseCase))

                // Inisiasi uploadcareRepo, uploadcareUseCase, dan uploadcareViewModel
                 val uploadcareRepo = UploadcareRepositoryImpl(this)
                 val uploadcareUseCase = UploadcareUseCase(uploadcareRepo)
                 val uploadcareViewModel: UploadcareViewModel = viewModel(factory = UploadcareViewModel.Factory(uploadcareUseCase))


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigation(navController) }
                ) {
                    NavHost(
                        navController = navController,
//                        ganti halaman start pertama di sini
//                        startDestination = "splash_screen"
                        startDestination = "home_screen"
//                        startDestination = "marketplace_screen"
                    ) {
                        composable("splash_screen") {
                            SplashScreen(navController)
                        }
                        composable("admin_login_screen") {
                            AdminLoginScreen(navController, authViewModel)
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
                            HomeScreen(navController, userProfileViewModel, moneyExchangeViewModel, authViewModel, productViewModel)
                        }
                        composable("maps_screen") {
                            MapsScreen(navController)
                        }
                        composable("marketplace_screen") {
                            MarketplaceScreen(navController, productViewModel)
                        }
                        composable("article_screen") {
                            ArticleScreen(navController)
                        }
                        composable("profile_screen") {
                            ProfileScreen(navController, userProfileViewModel)
                        }
                        composable("donate_screen") {
                            DonateScreen(navController, donationViewModel)
                        }
                        composable("donation_detail_screen/{donationTitle}") { backStackEntry ->
                            val donationTitle = backStackEntry.arguments?.getString("donationTitle") ?: ""
                            val decodedTitle = URLDecoder.decode(donationTitle, StandardCharsets.UTF_8.toString())

                            donationViewModel.getDonationByTitle(decodedTitle)
                            DonationDetailScreen(navController, donationViewModel, uploadcareViewModel)
                        }

                        composable("product_detail_screen/{productName}") { backStackEntry ->
                            val productName = backStackEntry.arguments?.getString("productName") ?: ""
                            val decodedName = URLDecoder.decode(productName, StandardCharsets.UTF_8.toString())

                            productViewModel.getProductByName(decodedName)
                            ProductDetailScreen(navController, productViewModel)
                        }
                        composable("cart_screen") {
                            CartScreen(navController, productViewModel)
                        }
                        composable("request_screen") {
                            RequestScreen(navController)
                        }
                        composable("edit_profile_screen") {
                            EditProfileScreen(navController, userProfileViewModel)
                        }
                        composable(
                            "payment_screen/{subtotal}/{shippingCost}/{total}",
                            arguments = listOf(
                                navArgument("subtotal") { type = NavType.IntType },
                                navArgument("shippingCost") { type = NavType.IntType },
                                navArgument("total") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val subtotal = backStackEntry.arguments?.getInt("subtotal") ?: 0
                            val shippingCost = backStackEntry.arguments?.getInt("shippingCost") ?: 0
                            val total = backStackEntry.arguments?.getInt("total") ?: 0
                            PaymentScreen(navController, subtotal, shippingCost, total, productViewModel, uploadcareViewModel)
                        }
                    }
                }
            }
        }
    }
}

