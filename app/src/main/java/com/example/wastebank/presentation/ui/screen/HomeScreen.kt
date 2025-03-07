package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.YellowMain

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenBg)
    ) {
        // dekorasi atas
        Image(
            painter = painterResource(id = R.drawable.decor_splash),
            contentDescription = "decoration",
            modifier = Modifier
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1.2f))

            // logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Sabi",
                modifier = Modifier.size(175.dp)
            )
            Spacer(modifier = Modifier.weight(2f))

            // button admin
            ButtonAuth(
                text = "MASUK SEBAGAI ADMIN",
                backgroundColor = BrownMain,
                onClick = { navController.navigate("admin_login_screen") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // button pengguna
            ButtonAuth(
                text = "MASUK SEBAGAI PENGGUNA",
                backgroundColor = YellowMain,
                onClick = { navController.navigate("user_login_screen") }
            )
            Spacer(modifier = Modifier.weight(0.8f))
        }
    }
}