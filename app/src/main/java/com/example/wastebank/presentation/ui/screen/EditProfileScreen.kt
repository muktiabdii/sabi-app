package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.domain.model.UserDomain
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.ui.theme.*
import com.example.wastebank.presentation.viewmodel.UserProfileViewModel

@Composable
fun EditProfileScreen(navController: NavController, userProfileViewModel: UserProfileViewModel) {
    // State untuk input field
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var points by remember { mutableStateOf(0) }
    var gender by remember { mutableStateOf("Wanita") } // Default ke Wanita

    // Ambil data user saat layar pertama kali dimuat
    val userProfile by userProfileViewModel.userProfile.collectAsState()

    LaunchedEffect(Unit) {
        userProfileViewModel.getUserProfile()
    }

    // Update state ketika data user tersedia
    LaunchedEffect(userProfile) {
        userProfile?.let {
            name = it.name
            phoneNumber = it.phoneNumber
            email = it.email
            gender = it.gender
            points = it.points
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(16.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Edit Profil",
                style = Typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Profile Picture
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(85.dp)
                        .background(color = BrownMain, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Profile Icon",
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Nama
            Text(text = "Nama", style = Typography.bodyLarge)
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldAuth(value = name, onValueChange = { name = it }, placeholder = "Masukkan nama Anda")

            Spacer(modifier = Modifier.height(8.dp))

            // No. Telepon
            Text(text = "No. Telepon", style = Typography.bodyLarge)
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldAuth(
                value = phoneNumber,
                onValueChange = {
                    if (it.all { char -> char.isDigit() || char == '+' }) phoneNumber = it
                },
                placeholder = "Masukkan nomor telepon",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Email
            Text(text = "Email", style = Typography.bodyLarge)
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldAuth(value = email, onValueChange = { email = it }, placeholder = "Masukkan email")

            Spacer(modifier = Modifier.height(8.dp))

            // Kata Sandi
            Text(text = "Kata Sandi", style = Typography.bodyLarge)
            Spacer(modifier = Modifier.height(5.dp))
            TextFieldPassword(value = password, onValueChange = { password = it }, placeholder = "Minimal 8 karakter")

            Spacer(modifier = Modifier.height(8.dp))

            // Gender Selection
            Text(text = "Gender", style = Typography.bodyLarge)
            Row {
                RadioButtonGender("Wanita", gender) { gender = it }
                Spacer(modifier = Modifier.width(5.dp))
                RadioButtonGender("Pria", gender) { gender = it }
            }
        }

        // Button Simpan Perubahan
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            ButtonAuth(
                text = "SIMPAN PERUBAHAN",
                backgroundColor = BrownMain,
                textColor = Color.White,
                onClick = {
                    val updatedUser = UserDomain(
                        name = name,
                        phoneNumber = phoneNumber,
                        email = email,
                        gender = gender,
                        points = points
                    )
                    userProfileViewModel.editUserProfile(updatedUser)
                    navController.navigate("profile_screen")
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewEditProfileScreen() {
//    EditProfileScreen(navController = rememberNavController())
//}
