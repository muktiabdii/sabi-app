package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.data.ProductDataSource
import com.example.wastebank.presentation.components.CardMyCart
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.component.PriceDetailRow
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyLine
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun CartScreen(navController: NavController) {
    val cartItems = ProductDataSource.productList
    val subtotal = cartItems.sumOf { it.price }
    val shippingCost = 0
    val total = subtotal + shippingCost

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(24.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            // Header
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
                    text = "Keranjangku",
                    style = Typography.headlineLarge,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(color = BrownMain, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bag),
                        contentDescription = "Cart",
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // List Produk dalam Keranjang
            cartItems.forEachIndexed { index, product ->
                CardMyCart(product = product, quantity = 1)
                HorizontalDivider(color = GreyLine, thickness = 1.dp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Detail Harga
            Text(
                text = "Detail Harga",
                style = Typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            PriceDetailRow(label = "Subtotal", amount = subtotal)
            Spacer(modifier = Modifier.height(4.dp))
            PriceDetailRow(label = "Pengiriman", amount = shippingCost)
            Spacer(modifier = Modifier.height(4.dp))
            PriceDetailRow(label = "Total", amount = total)
        }

        // Tombol Pembelian
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            ButtonAuth(
                text = "PEMBELIAN",
                backgroundColor = BrownMain,
                textColor = Color.White,
                onClick = { navController.navigate("payment_screen") }
            )
        }
        Spacer(modifier = Modifier.height(90.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCartScreen() {
    CartScreen(navController = rememberNavController())
}
