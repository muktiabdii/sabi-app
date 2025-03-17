package com.example.wastebank.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.domain.model.Product
import com.example.wastebank.domain.model.ProductCategory
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.viewmodel.ProductViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MarketplaceScreen(navController: NavController, productViewModel: ProductViewModel) {
    // state input search
    var searchText by remember { mutableStateOf("") }

    // state product
    val products by productViewModel.products.collectAsState()

    Scaffold(
        // bottom navigation
        bottomBar = { BottomNavigation(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                // search bar dan filter
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SearchBarMarket(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = "Cari Produk Olahan Kamu",
                        modifier = Modifier.weight(1f)
                    )
                    ButtonFilter(onClick = { })
                }
            }

            item {
                // slideshow donasi
                BannerDonation(navController)
            }

            item {
                // wrapper
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    // grid product
                    LazyVerticalGrid(
                        // 2 cell per baris
                        columns = GridCells.Fixed(2),
                        // jarak antar cell
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        contentPadding = PaddingValues(top = 10.dp, bottom = 20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 1600.dp)

                    ) {
                        items(products) { product ->
                            CardProduct(
                                product = product,
                                modifier = Modifier.height(200.dp),
                                imageHeight = 120,
                                onClick = {
                                    val encodedName = URLEncoder.encode(product.name, StandardCharsets.UTF_8.toString())
                                    navController.navigate("product_detail_screen/$encodedName")
                                },
                                onAddToCart = { }
                            )
                        }
                    }
                }
            }
        }
    }
}

//fun getProductList(): List<Product> {
//    return listOf(
//        Product("Pot Bunga Hewan", ProductCategory.VASE, "Rp 20.000", R.drawable.product_pot),
//        Product(
//            "Tas Totebag Jeans",
//            ProductCategory.FASHION,
//            "Rp 15.000",
//            R.drawable.product_totebag
//        ),
//        Product("Lampu Sendok", ProductCategory.DECOR, "Rp 30.000", R.drawable.product_lampu),
//        Product("Mainan Mobil", ProductCategory.TOY, "Rp 25.000", R.drawable.product_mobil),
//        Product("Tempat Pensil", ProductCategory.CRAFT, "Rp 30.000", R.drawable.product_pensil),
//        Product("Pot Bunga Hewan", ProductCategory.VASE, "Rp 20.000", R.drawable.product_pot),
//        Product(
//            "Tas Totebag Jeans",
//            ProductCategory.FASHION,
//            "Rp 15.000",
//            R.drawable.product_totebag
//        ),
//        Product("Lampu Sendok", ProductCategory.DECOR, "Rp 30.000", R.drawable.product_lampu),
//        Product("Mainan Mobil", ProductCategory.TOY, "Rp 25.000", R.drawable.product_mobil),
//        Product("Tempat Pensil", ProductCategory.CRAFT, "Rp 30.000", R.drawable.product_pensil),
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun PreviewMarketplaceScreen() {
//    val navController = rememberNavController()
//    MarketplaceScreen(navController)
//}