package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.wastebank.presentation.ui.theme.YellowMain
import com.example.wastebank.presentation.viewmodel.AuthViewModel

@Composable
fun BottomNavigation(navController: NavController, authViewModel: AuthViewModel) {
    val role by authViewModel.role.collectAsState()

    // daftar item bottom nav berdasarkan role
    val items = if (role == "admin") {
        listOf(
            BottomNavItem.HomeAdmin,
            BottomNavItem.MapsAdmin,
            BottomNavItem.MarketAdmin,
            BottomNavItem.ArticleAdmin,
            BottomNavItem.ProfileAdmin
        )
    } else {
        listOf(
            BottomNavItem.Home,
            BottomNavItem.Maps,
            BottomNavItem.Market,
            BottomNavItem.Article,
            BottomNavItem.Profile
        )
    }

    // pisah daftar route untuk user dan admin
    val sectionRoutes = if (role == "admin") {
        mapOf(
            BottomNavItem.HomeAdmin.route to listOf("admin_home_screen"),
            BottomNavItem.MapsAdmin.route to listOf("manage_request_screen"),
            BottomNavItem.MarketAdmin.route to listOf("admin_marketplace_screen"),
            BottomNavItem.ArticleAdmin.route to listOf("article_screen"),
            BottomNavItem.ProfileAdmin.route to listOf("profile_screen", "edit_profile_screen")
        )
    } else {
        mapOf(
            BottomNavItem.Home.route to listOf("home_screen"),
            BottomNavItem.Maps.route to listOf("maps_screen", "request_screen"),
            BottomNavItem.Market.route to listOf("marketplace_screen", "donate_screen"),
            BottomNavItem.Article.route to listOf("article_screen"),
            BottomNavItem.Profile.route to listOf("profile_screen", "edit_profile_screen")
        )
    }

    // halaman yang tidak menampilkan BottomNav
    val hiddenScreens = listOf(
        "splash_screen",
        "onboarding_screen",
        "login_role_screen",
        "admin_login_screen",
        "user_login_screen",
        "register_screen",
        "forgot_password_screen",
        "set_new_password_screen",
        "cart_screen",
        "donation_detail_screen",
        "payment_screen",
        "product_detail_screen",
        "input_trash_screen"
    )

    // ambil halaman saat ini
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    val shouldShowBottomNav = !hiddenScreens.any { currentRoute.startsWith(it) }

    if (shouldShowBottomNav) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .border(
                    width = 1.dp,
                    color = YellowMain,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .padding(1.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            color = Color.White,
            shadowElevation = 10.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    val isSelected = sectionRoutes[item.route]?.contains(currentRoute) == true

                    Box(
                        modifier = Modifier.size(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = {
                                if (!isSelected) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                painter = painterResource(id = if (isSelected) item.icon else item.iconOff),
                                contentDescription = item.title,
                                tint = YellowMain
                            )
                        }
                    }
                }
            }
        }
    }
}

