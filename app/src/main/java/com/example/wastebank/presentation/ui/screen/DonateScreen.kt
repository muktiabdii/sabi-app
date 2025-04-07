package com.example.donation.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.wastebank.presentation.viewmodel.AuthViewModel
import com.example.wastebank.presentation.viewmodel.DonationViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun DonateScreen(navController: NavController, donationViewModel: DonationViewModel, authViewModel: AuthViewModel) {
    var searchText by remember { mutableStateOf("") }

    // state donation
    val donations by donationViewModel.donations.collectAsState()

    LaunchedEffect(Unit) {
        donationViewModel.getAllDonations()
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(navController, authViewModel)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    ) {
                        // gambar donasi papua
                        Image(
                            painter = painterResource(id = R.drawable.donate_papua),
                            contentDescription = "Papua Dengan Kita",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.3f))
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            // search bar
                            SearchBarMarket(
                                value = searchText,
                                onValueChange = { searchText = it },
                                placeholder = "Cari Tempat Donasi Kamu",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            // teks di bawah gambar
                            Column(
                                // pindah ke donation detail
                                modifier = Modifier.clickable {
                                    navController.navigate("donation_detail_screen")
                                }
                            ) {
                                Text(
                                    text = "Papua Dengan Kita",
                                    style = Typography.headlineLarge,
                                    color = Color.White
                                )
                                Text(
                                    text = "Suru-Suru, Koba, Papua",
                                    style = Typography.bodyLarge,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Tempat Donasi Untuk Kamu",
                        style = Typography.headlineLarge,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
                    ) {
                        items(donations) { donation ->
                            CardDonate(
                                donation = donation,
                                onClick = {
                                    val encodedTitle = URLEncoder.encode(donation.title, StandardCharsets.UTF_8.toString())
                                    navController.navigate("donation_detail_screen/$encodedTitle")
                                }
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = "Donasi Seluruh Indonesia",
                        style = Typography.headlineLarge,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }

                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 85.dp
                        )
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
            }

            // box untuk membungkus tombol
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
            ) {
                ButtonAuth(
                    text = "KEMBALI KE MARKETPLACE",
                    backgroundColor = BrownMain,
                    textColor = Color.White,
                    // pindah ke marketplace
                    onClick = {
                        navController.navigate("marketplace_screen") {
                            popUpTo("donate_screen") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewDonateScreen() {
//    DonateScreen(navController = rememberNavController())
//}


