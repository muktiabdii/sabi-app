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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.wastebank.R
import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.theme.*
import com.example.wastebank.presentation.viewmodel.ProductViewModel

@Composable
fun ProductDetailScreen(navController: NavController, productViewModel: ProductViewModel) {
    val product by productViewModel.selectedProduct.collectAsState()
    val point = (product?.price ?: 0) / 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.height(500.dp)) {
            AsyncImage(
                model = product?.image ?: "",
                contentDescription = "gambar produk",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Tombol back
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

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            // Jika product null, tampilkan teks placeholder
            Text(text = product?.name ?: "Nama Produk", style = Typography.headlineLarge.copy(fontSize = 24.sp))
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = product?.category ?: "Kategori",
                style = Typography.headlineMedium,
                color = GreyMedium
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = GreyLine
            )

            // Harga produk
            Text(text = product?.formatRupiah() ?: "Rp 0", style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Setara dengan $point poin",
                style = Typography.bodyLarge,
                color = GreyMedium
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = GreyLine
            )

            Text(text = "Tentang Produk", style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = product?.description ?: "Deskripsi tidak tersedia",
                style = Typography.bodyLarge,
                color = GreyMedium,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(32.dp))

            ButtonAuth(
                text = "MASUKKAN KERANJANG",
                onClick = {
                    product?.let { productViewModel.addToCart(it) }
                    navController.navigate("cart_screen")
                }
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ProductDetailScreenPreview() {
//    val navController = rememberNavController()
//    ProductDetailScreen(
//        navController = navController,
//        product = ProductDataSource.productList[0],
//    )
//}
