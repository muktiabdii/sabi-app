package com.example.donation.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.presentation.ui.component.BottomNavigation
import com.example.wastebank.presentation.ui.component.SearchBarMarket
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.component.CardDestination
import com.example.wastebank.presentation.ui.component.CardDonate
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun DonateScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                // Search Bar
                SearchBarMarket(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = "Cari Tempat Donasi Kamu",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            item {
                // Banner Image
                Image(
                    painter = painterResource(id = R.drawable.donate_papua),
                    contentDescription = "Papua Dengan Kita",
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Papua Dengan Kita",
                        style = Typography.headlineLarge
                    )
                    Text(
                        text = "Suru-Suru, Koba, Papua",
                        style = Typography.bodyLarge
                    )
                }
            }

            item {
                // Section Title
                Text(
                    text = "Tempat Donasi Untuk Kamu",
                    style = Typography.headlineLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            item {
                // Donation List
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    item {
                        CardDonate(
                            imageResId = R.drawable.donate_rumah_portrait,
                            title = "Rumah Dhuafa Ayna",
                            description = "Bantu anak-anak untuk beli peralatan sekolah"
                        )
                    }
                    item {
                        CardDonate(
                            imageResId = R.drawable.donate_papua_portrait,
                            title = "Papua Dengan Kita",
                            description = "Beri subsidi untuk mereka yang berada di Timur!"
                        )
                    }
                    item {
                        CardDonate(
                            imageResId = R.drawable.donate_mengajar_portrait,
                            title = "Mengajar Pelosok",
                            description = "Dukung para relawan untuk membeli kebutuhan."
                        )
                    }
                }
            }

            item {
                // Section Title
                Text(
                    text = "Donasi Seluruh Indonesia",
                    style = Typography.headlineLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            item {
                // Destination List
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    item {
                        CardDestination(imageResId = R.drawable.dest_bali, title = "Bali")
                    }
                    item {
                        CardDestination(imageResId = R.drawable.dest_labuan, title = "Labuan Bajo")
                    }
                    item {
                        CardDestination(imageResId = R.drawable.dest_lombok, title = "Lombok")
                    }
                }
            }

            item {
                // Button Kembali ke Marketplace
                ButtonAuth(
                    text = "KEMBALI KE MARKETPLACE",
                    backgroundColor = BrownMain,
                    textColor = Color.White,
                    onClick = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDonateScreen() {
    DonateScreen(navController = rememberNavController())
}
