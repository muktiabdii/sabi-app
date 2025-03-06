package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.YellowMain

@Composable
fun SplashScreen(onAdminClick: () -> Unit, onUserClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = GreenBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.decor_lower),
                contentDescription = "Logo Sabi",
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.weight(2f))
            ButtonAuth(
                text = "MASUK SEBAGAI ADMIN",
                backgroundColor = BrownMain,
                onClick = onAdminClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonAuth(
                text = "MASUK SEBAGAI PENGGUNA",
                backgroundColor = YellowMain,
                onClick = onUserClick,
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(onAdminClick = {}, onUserClick = {})
}