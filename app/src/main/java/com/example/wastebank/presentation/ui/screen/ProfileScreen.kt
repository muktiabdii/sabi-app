package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.CardProfileMenu
import com.example.wastebank.presentation.ui.component.CardProfileNotif
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.RedBg
import com.example.wastebank.presentation.viewmodel.UserProfileViewModel

@Composable
fun ProfileScreen(navController: NavController, userProfileViewModel: UserProfileViewModel) {
    val userProfile by userProfileViewModel.userProfile.collectAsState()

    LaunchedEffect(Unit) {
        userProfileViewModel.getUserProfile()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        // Header Profil
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icon Profil
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(color = BrownMain, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "User Icon",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(text = userProfile?.name.orEmpty(), style = Typography.headlineMedium)
                    Text(text = "Jakarta, Indonesia", style = Typography.bodyLarge)
                }
            }

            // Edit Profil
            Text(
                text = "Edit profil",
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                color = BrownMain,
                modifier = Modifier.clickable { navController.navigate("edit_profile_screen") }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Seputar Aplikasi
        Text(text = "Seputar Aplikasi", style = Typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))

        // Pusat Bantuan
        CardProfileMenu(
            iconResId = R.drawable.ic_help,
            backgroundColor = GreenBg,
            text = "Pusat Bantuan & FAQ",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(12.dp))

        // state keaktifan notifikasi
        var isNotifEnabled by remember { mutableStateOf(true) }

        // Notifikasi
        CardProfileNotif(
            iconResId = R.drawable.ic_notif,
            backgroundColor = GreenBg,
            text = "Notifikasi",
            isChecked = isNotifEnabled,
            onCheckedChange = { isNotifEnabled = it }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Pengaturan
        Text(text = "Pengaturan", style = Typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))

        // Hapus Akun
        CardProfileMenu(
            iconResId = R.drawable.ic_delete,
            backgroundColor = RedBg,
            text = "Hapus Akun",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Keluar
        CardProfileMenu(
            iconResId = R.drawable.ic_exit,
            backgroundColor = GreenBg,
            text = "Keluar",
            onClick = { navController.navigate("splash_screen") }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewProfileScreen() {
//    val navController = rememberNavController()
//    ProfileScreen(navController)
//}