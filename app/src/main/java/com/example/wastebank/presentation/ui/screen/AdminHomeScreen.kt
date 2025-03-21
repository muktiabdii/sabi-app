package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.YellowMain

@Composable
fun AdminHomeScreen(navController: NavController) {
    val orderList = listOf(
        OrderItem(1, "Tobby", "Produk", "Done"),
        OrderItem(2, "Fajar", "Donasi", "Process"),
        OrderItem(3, "Abdi", "Produk", "To Do")
    )

    val pickupList = listOf(
        PickupItem("Tobby", "Lowokwaru", "14/03/2025"),
        PickupItem("Fajar", "Mojokerto", "14/03/2025"),
        PickupItem("Abdi", "Lowokwaru", "14/03/2025")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(YellowMain)
    ) {
        // Top bar
        TopBar(username = "Admin", role = "admin")

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                // Section: Permintaan Pesanan
                Text(
                    text = "Permintaan Pesanan",
                    style = Typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TableOrder(orderList)

                Spacer(modifier = Modifier.height(24.dp))

                // Section: Lihat Pickup
                Text(
                    text = "Lihat Pickup",
                    style = Typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TablePickup(pickupList)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdminHomeScreen() {
    val navController = rememberNavController()
    AdminHomeScreen(navController = navController)
}
