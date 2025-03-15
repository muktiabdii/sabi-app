package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun ProfileScreen(navController: NavController) {
//    ON PROGRESS
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Screen", style = Typography.headlineLarge)
    }
}