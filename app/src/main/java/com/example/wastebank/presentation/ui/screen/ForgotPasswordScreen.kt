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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.component.PopUpNotif
import com.example.wastebank.presentation.ui.component.TextFieldAuth
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.TextRed
import com.example.wastebank.presentation.ui.theme.manrope
import com.example.wastebank.presentation.viewmodel.AuthViewModel

@Composable
fun ForgotPasswordScreen(navController: NavController, authViewModel: AuthViewModel) {
    val email by authViewModel.email.collectAsState()
    var showPopUp by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenBg)
    ) {
        // Tombol Back
        IconButton(
            onClick = { navController.popBackStack() },
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
                text = "Lupa kata sandi?",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Jangan khawatir! Itu bisa saja terjadi. Silakan masukkan email yang terkait dengan akun Anda.",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                    textAlign = TextAlign.Justify,
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            // TextFields Email
            Text(
                text = "Email",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                    textAlign = TextAlign.Start,
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldAuth(
                value = email,
                placeholder = "Masukkan alamat email",
                onValueChange = {
                    authViewModel.updateEmail(it)
                })
            Spacer(modifier = Modifier.height(190.dp))

            // Button kirim kode
            ButtonAuth(
                text = "KIRIM KODE",
                onClick = {
                    authViewModel.resetPassword { success, message ->
                        if (success) {
                            showPopUp = true
                        }

                        else {
                            println("Gagal mengirim kode: $message")
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Ingat kata sandi
            LoginText(navController)
        }

        if (showPopUp) {
            PopUpNotif(
                iconResId = R.drawable.ic_success,
                message = "Permintaan terkirim!",
                buttonText = "KEMBALI MASUK",
                onDismiss = {
                    showPopUp = false
                    navController.navigate("user_login_screen")
                }
            )
        }
    }
}

// Button Masuk
@Composable
fun LoginText(navController: NavController) {
    val annotatedText = buildAnnotatedString {
        append("Ingat Kata Sandi? ")

        pushStringAnnotation(tag = "login", annotation = "login")
        withStyle(
            style = SpanStyle(color = TextRed)
        ) {
            append("Masuk")
        }
        pop()
    }

    Text(
        text = annotatedText,
        fontSize = 12.sp,
        color = GreyMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("user_login_screen") }
    )
}

//@Preview
//@Composable
//fun PreviewForgotPasswordScreen() {
//    val navController = rememberNavController()
//    ForgotPasswordScreen(navController)
//}