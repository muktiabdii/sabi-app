package com.example.wastebank.presentation.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.domain.model.ProductCategory
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.viewmodel.AuthViewModel
import com.example.wastebank.presentation.viewmodel.ProductViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMarketplaceScreen(navController: NavController, productViewModel: ProductViewModel, authViewModel: AuthViewModel) {
    // State input search
    var searchText by remember { mutableStateOf("") }

    // State produk
    val products by productViewModel.products.collectAsState()

    // State modal bottom sheet
    var isSheetOpen by remember { mutableStateOf(false) }
    val sheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = true) // Langsung full height
    val coroutineScope = rememberCoroutineScope()

    // State produk baru
    var productName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var productImage by remember { mutableStateOf<String?>(null) }
    var description by remember { mutableStateOf("") }

    val categoryList = ProductCategory.values().map { it.displayName }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        productImage = uri?.toString()
    }

    LaunchedEffect(Unit) {
        productViewModel.getProducts()
        productViewModel.getCartItems()
    }

    Scaffold(
        bottomBar = { BottomNavigation(navController, authViewModel) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                // Search bar dan filter
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SearchBarMarket(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = "Cari Produk Olahan",
                        modifier = Modifier.weight(1f)
                    )
                    ButtonFilter(onClick = { })
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    ButtonAuth(
                        text = "TAMBAHKAN PRODUK BARU",
                        onClick = { isSheetOpen = true }
                    )
                }
            }

            item {
                // Slideshow donasi
                BannerDonation(navController)
            }

            item {
                // Wrapper produk
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
                            .heightIn(min = 100.dp, max = 10000.dp)
                    ) {
                        items(products) { product ->
                            CardProduct(
                                product = product,
                                modifier = Modifier.height(230.dp),
                                imageHeight = 125,
                                onClick = {
                                    val encodedName = URLEncoder.encode(
                                        product.name,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate("product_detail_screen/$encodedName")
                                },
                                onAddToCart = { product?.let { productViewModel.addToCart(it) } }
                            )
                        }
                    }
                }
            }
        }
    }

    // Modal Bottom Sheet untuk Tambah Produk Baru
    if (isSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isSheetOpen = false },
            sheetState = sheetState,
            dragHandle = null
        ) {
            BtmSheetAddProduct(
                categoryList = categoryList,
                onAddProduct = {
                    isSheetOpen = false
                }
            )
        }
    }
}

