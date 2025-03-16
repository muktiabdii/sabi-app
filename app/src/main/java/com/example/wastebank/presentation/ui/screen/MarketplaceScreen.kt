package com.example.wastebank.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.data.ProductDataSource
import com.example.wastebank.presentation.ui.component.*

@Composable
fun MarketplaceScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
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
                BannerDonation(navController)
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        contentPadding = PaddingValues(top = 10.dp, bottom = 20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 1600.dp)
                    ) {
                        items(ProductDataSource.productList) { product ->
                            CardProduct(
                                product = product,
                                modifier = Modifier.height(210.dp),
                                imageHeight = 125,
                                onClick = { navController.navigate("product_detail_screen") },
                                onAddToCart = { }
                            )
                        }
                        items(ProductDataSource.productList) { product ->
                            CardProduct(
                                product = product,
                                modifier = Modifier.height(210.dp),
                                imageHeight = 125,
                                onClick = { navController.navigate("product_detail_screen") },
                                onAddToCart = { }
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMarketplaceScreen() {
    val navController = rememberNavController()
    MarketplaceScreen(navController)
}
