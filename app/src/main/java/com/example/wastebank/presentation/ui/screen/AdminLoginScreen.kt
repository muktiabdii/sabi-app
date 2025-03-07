package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.component.TextFieldAuth
import com.example.wastebank.presentation.ui.component.TextFieldPassword
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.manrope

@Composable
fun AdminLoginScreen(navController: NavController) {
    var emailState by remember { mutableStateOf("") }
    var idState by remember { mutableStateOf("") }
    var sandiState by remember { mutableStateOf("") }

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
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Masuk Sebagai Admin",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // TextFields Email
            Text(
                text = "Email atau No. Telepon",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldAuth(
                value = emailState,
                placeholder = "Masukkan alamat email",
                onValueChange = {
                    emailState = it
                })
            Spacer(modifier = Modifier.height(8.dp))

            // TextFields ID Admin
            Text(
                text = "ID Admin",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldAuth(value = idState,
                placeholder = "Masukkan ID email",
                onValueChange = {
                    idState = it
                })
            Spacer(modifier = Modifier.height(8.dp))

            // TextFields Kata Sandi
            Text(
                text = "Kata Sandi",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                    textAlign = TextAlign.Start,
                )
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

            // Lupa Sandi
            Text(
                text = "Lupa kata sandi?",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("forgot_password_screen") },
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                    textAlign = TextAlign.End,
                )
            )
            Spacer(modifier = Modifier.height(90.dp))

            // Button Masuk
            ButtonAuth(
                text = "MASUK",
                onClick = { navController.navigate("home_screen") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Sudah Punya Akun
            RegisterText(navController)
        }
    }
}

@Preview
@Composable
fun PreviewAdminLoginScreen() {
    val navController = rememberNavController()
    AdminLoginScreen(navController)
}