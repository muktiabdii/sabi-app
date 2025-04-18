package com.example.wastebank.presentation.ui.screen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.component.TextFieldAuth
import com.example.wastebank.presentation.ui.component.TextFieldPassword
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.viewmodel.AuthViewModel

@Composable
fun AdminLoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    val email by authViewModel.email.collectAsState()
    val adminId by authViewModel.adminId.collectAsState()
    val password by authViewModel.password.collectAsState()
    val errorMessage by authViewModel.errorMessage.collectAsState()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    val context = LocalContext.current

    // Menampilkan Toast jika ada error message
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            authViewModel.resetErrorMessage()
        }
    }

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            authViewModel.clearEmailInput()
            authViewModel.clearPasswordInput()
            authViewModel.clearAdminIdInput()
            navController.navigate("admin_home_screen")
            authViewModel.resetIsLoggedIn()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenBg)
    ) {
        // Tombol Back
        IconButton(
            onClick = { navController.navigate("login_role_screen") },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 30.dp, vertical = 55.dp)
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
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "Masuk Sebagai Admin",
                style = Typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(32.dp))

            // TextFields Email
            Text(
                text = "Email atau No. Telepon",
                style = Typography.bodyLarge.copy(color = GreyMedium)
            )
            Spacer(modifier = Modifier.height(6.dp))
            TextFieldAuth(
                value = email,
                placeholder = "Masukkan alamat email",
                onValueChange = {
                    authViewModel.updateEmail(it)
                })
            Spacer(modifier = Modifier.height(10.dp))

            // TextFields ID Admin
            Text(
                text = "ID Admin",
                style = Typography.bodyLarge.copy(color = GreyMedium)
            )
            Spacer(modifier = Modifier.height(6.dp))
            TextFieldAuth(
                value = adminId,
                placeholder = "Masukkan ID admin",
                onValueChange = {
                    authViewModel.updateAdminId(it)
                })
            Spacer(modifier = Modifier.height(10.dp))

            // TextFields Kata Sandi
            Text(
                text = "Kata Sandi",
                style = Typography.bodyLarge.copy(color = GreyMedium)
            )
            Spacer(modifier = Modifier.height(6.dp))
            TextFieldPassword(
                value = password,
                placeholder = "Masukkan kata sandi",
                onValueChange = {
                    authViewModel.updatePassword(it)
                },
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Lupa Sandi
            Text(
                text = "Lupa kata sandi?",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("forgot_password_screen") },
                style = Typography.bodyLarge.copy(color = GreyMedium, textAlign = TextAlign.End)

            )
            Spacer(modifier = Modifier.height(135.dp))

            // Button Masuk
            ButtonAuth(
                text = "MASUK",
                onClick = {
                    authViewModel.loginAdmin()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Sudah Punya Akun
            RegisterText(navController)
        }
    }
}

//@Preview
//@Composable
//fun PreviewAdminLoginScreen() {
//    val navController = rememberNavController()
//    AdminLoginScreen(navController)
//}