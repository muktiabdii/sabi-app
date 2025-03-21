package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.component.TextFieldPassword
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun SetNewPasswordScreen(navController: NavController) {
    var sandiState by remember { mutableStateOf("") }
    var ulangSandiState by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenBg)
    ) {
        // Tombol Back
        IconButton(
            onClick = { navController.navigate("splash_screen") },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(30.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "back"
            )
        }

        // Dekorasi atas
        Image(
            painter = painterResource(id = R.drawable.decor_top),
            contentDescription = "decoration",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 10.dp)
        )

        // Dekorasi bawah
        Image(
            painter = painterResource(id = R.drawable.decor_lower),
            contentDescription = "decoration",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .scale(1.15f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(44.dp)
        ) {
            Spacer(modifier = Modifier.height(115.dp))

            Text(
                text = "Setel ulang kata sandi",
                style = Typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Silakan ketik sesuatu yang dapat Anda ingat.",
                style = Typography.bodySmall
            )
            Spacer(modifier = Modifier.height(24.dp))

            // TextFields kata sandi
            Text(
                text = "Kata sandi baru",
                style = Typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldPassword(
                value = sandiState,
                placeholder = "Masukkan kata sandi",
                onValueChange = {
                    sandiState = it
                },
            )
            Spacer(modifier = Modifier.height(8.dp))

            // TextFields konfirmasi kata sandi
            Text(
                text = "Konfirmasi kata sandi baru",
                style = Typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldPassword(
                value = ulangSandiState,
                placeholder = "Masukkan kata sandi",
                onValueChange = {
                    ulangSandiState = it
                },
            )
            Spacer(modifier = Modifier.height(130.dp))

            // Button atur ulang
            ButtonAuth(
                text = "ATUR ULANG KATA SANDI",
                onClick = { navController.navigate("user_login_screen") }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSetNewPasswordScreen() {
    val navController = rememberNavController()
    SetNewPasswordScreen(navController)
}