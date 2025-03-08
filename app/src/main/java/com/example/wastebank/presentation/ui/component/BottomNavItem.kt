package com.example.wastebank.presentation.ui.component

import com.example.wastebank.R

sealed class BottomNavItem(val route: String, val title: String, val icon: Int, val iconOff: Int) {
    object Home : BottomNavItem("home", "Home", R.drawable.ic_home, R.drawable.ic_home_off)
    object Maps : BottomNavItem("maps", "Maps", R.drawable.ic_maps, R.drawable.ic_maps_off)
    object Market : BottomNavItem("market", "Market", R.drawable.ic_market, R.drawable.ic_market_off)
    object Article : BottomNavItem("article", "Article", R.drawable.ic_article, R.drawable.ic_article_off)
    object Profile : BottomNavItem("profile", "Profile", R.drawable.ic_profile, R.drawable.ic_profile_off)
}