package com.example.wastebank.ui.splash

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.wastebank.presentation.ui.component.TextFieldAuth
import com.example.wastebank.presentation.ui.component.TextFieldPassword
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.TextRed
import com.example.wastebank.presentation.ui.theme.manrope
import com.example.wastebank.presentation.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {

    val name by authViewModel.name.collectAsState()
    val email by authViewModel.email.collectAsState()
    val password by authViewModel.password.collectAsState()
    val phoneNumber by authViewModel.phoneNumber.collectAsState()
    val gender by authViewModel.gender.collectAsState()
    val errorMessage by authViewModel.errorMessage.collectAsState()
    val isRegistered by authViewModel.isRegistered.collectAsState()
    val context = LocalContext.current

    // Menampilkan Toast jika ada error message
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            authViewModel.resetErrorMessage()
        }
    }

    // Berpindah halaman ke halaman login jika registrasi berhasil
    LaunchedEffect(isRegistered) {
        if (isRegistered) {
            navController.navigate("user_login_screen")
            authViewModel.resetIsRegistered()
        }
    }

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
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Bergabung dengan SABI",
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
                text = "Jadilah bagian dari gerakan SABI dalam mengelola sampah organik, berkolaborasi dengan sesama peduli lingkungan, dan raih berbagai reward menarik dari kontribusimu.",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                    textAlign = TextAlign.Justify,
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            // TextFields Nama
            Text(
                text = "Nama",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldAuth(
                value = name,
                placeholder = "Masukkan nama Anda",
                onValueChange = {
                    authViewModel.updateName(it)
                })
            Spacer(modifier = Modifier.height(8.dp))

            // TextFields No. Telepon
            Text(
                text = "No. Telepon",
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
                value = phoneNumber,
                placeholder = "Masukkan nomor telepon",
                onValueChange = {
                    val regex = Regex("^\\+?\\d{0,15}$") // hanya input angka
                    if (regex.matches(it)) {
                        authViewModel.updatePhoneNumber(it)
                    }
                })
            Spacer(modifier = Modifier.height(8.dp))

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
                placeholder = "Masukkan email",
                onValueChange = {
                    authViewModel.updateEmail(it)
                })
            Spacer(modifier = Modifier.height(8.dp))

            // TextFields kata sandi
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
                value = password,
                placeholder = "Minimal 8 karakter",
                onValueChange = {
                    authViewModel.updatePassword(it)
                },
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Gender Selection
            Text(
                text = "Gender",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = manrope,
                    fontWeight = FontWeight.Normal,
                    color = GreyMedium,
                )
            )
            Row {
                GenderRadioButton("Wanita", gender) {
                    authViewModel.updateGender(it)
                }
                Spacer(modifier = Modifier.width(5.dp))
                GenderRadioButton("Pria", gender) {
                    authViewModel.updateGender(it)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            // Button Daftar
            ButtonAuth(
                text = "DAFTAR",
                onClick = {
                    authViewModel.register()
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Sudah Punya Akun
            LoginText(navController)
        }
    }
}

// Radio Button Gender
@Composable
fun GenderRadioButton(label: String, selected: String, onSelect: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected == label,
            onClick = { onSelect(label) },
            colors = RadioButtonDefaults.colors(
                selectedColor = BrownMain,
                unselectedColor = GreyMedium
            )
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

// Button Masuk
@Composable
fun LoginText(navController: NavController) {
    val annotatedText = buildAnnotatedString {
        append("Sudah Punya Akun? ")

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
//fun PreviewRegisterScreen() {
//    val navController = rememberNavController()
//    val fakeAuthViewModel = remember { AuthViewModel() } // Gunakan instance lokal
//    RegisterScreen(authViewModel = fakeAuthViewModel, navController)
//}