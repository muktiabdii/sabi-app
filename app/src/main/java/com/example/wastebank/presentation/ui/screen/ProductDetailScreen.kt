package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.data.ProductDataSource
import com.example.wastebank.domain.model.Product
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.theme.*

@Composable
fun ProductDetailScreen(
    navController: NavController,
    product: Product
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = "Product Image",
                modifier = Modifier.fillMaxWidth()
            )

            // tombol back
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 40.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.TopStart)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = BrownMain
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            // nama produk
            Text(text = product.name, style = Typography.headlineLarge.copy(fontSize = 24.sp))
            Spacer(modifier = Modifier.height(2.dp))

            // kategori produk
            Text(
                text = product.category.displayName,
                style = Typography.headlineMedium,
                color = GreyMedium
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = GreyLine
            )

            // harga produk
            Text(text = product.formattedPrice, style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(4.dp))

            // nilai poin
            Text(
                text = "Setara dengan ${product.pointsEquivalent} poin",
                style = Typography.bodyLarge,
                color = GreyMedium
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = GreyLine
            )

            // tentang produk
            Text(text = "Tentang Produk", style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = product.description,
                style = Typography.bodyLarge,
                color = GreyMedium,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(32.dp))

            // button masukkan keranjang
            ButtonAuth(
                text = "MASUKKAN KERANJANG",
                onClick = { navController.navigate("cart_screen") }
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    val navController = rememberNavController()
    ProductDetailScreen(
        navController = navController,
        product = ProductDataSource.productList[0],
    )
}
