package com.example.wastebank.presentation.ui.component

import com.example.wastebank.R

sealed class BottomNavItem(val route: String, val title: String, val icon: Int, val iconOff: Int) {
    // admin
    object HomeAdmin : BottomNavItem("admin_home_screen", "Home", R.drawable.ic_home, R.drawable.ic_home_off)
    object MapsAdmin : BottomNavItem("manage_request_screen", "Maps", R.drawable.ic_maps, R.drawable.ic_maps_off)
    object MarketAdmin : BottomNavItem("admin_marketplace_screen", "Market", R.drawable.ic_market, R.drawable.ic_market_off)
    object ArticleAdmin : BottomNavItem("article_screen", "Article", R.drawable.ic_article, R.drawable.ic_article_off)
    object ProfileAdmin : BottomNavItem("profile_screen", "Profile", R.drawable.ic_profile, R.drawable.ic_profile_off)

    // user
    object Home : BottomNavItem("home_screen", "Home", R.drawable.ic_home, R.drawable.ic_home_off)
    object Maps : BottomNavItem("maps_screen", "Maps", R.drawable.ic_maps, R.drawable.ic_maps_off)
    object Market : BottomNavItem("marketplace_screen", "Market", R.drawable.ic_market, R.drawable.ic_market_off)
    object Article : BottomNavItem("article_screen", "Article", R.drawable.ic_article, R.drawable.ic_article_off)
    object Profile : BottomNavItem("profile_screen", "Profile", R.drawable.ic_profile, R.drawable.ic_profile_off)
}