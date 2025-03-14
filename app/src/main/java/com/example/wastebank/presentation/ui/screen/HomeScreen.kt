package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.domain.model.Product
import com.example.wastebank.domain.model.ProductCategory
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.YellowMain
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    // kelola state bottom sheet tukar poin
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isSheetOpen by remember { mutableStateOf(false) }
    var currentStep by remember { mutableStateOf(1) }
    val coroutineScope = rememberCoroutineScope()

    // state pop up
    var showPopup by remember { mutableStateOf(false) }

    LaunchedEffect(isSheetOpen) {
        if (isSheetOpen && !sheetState.isVisible) {
            coroutineScope.launch {
                sheetState.show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(YellowMain)
    ) {
        // topbar
        TopBar(username = "Raion", points = 2450)

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(22.dp))

                // card poin
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    CardPoint(
                        points = 2540,
                        onViewPointsClick = { },
                        onRedeemPointsClick = { isSheetOpen = true } // buka bottom sheet
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // misi kamu hari ini
                Text(
                    text = "Misi Kamu Hari Ini",
                    style = Typography.headlineLarge,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // card kampanye bulan maret
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    CardCampaign(
                        monthName = "Maret",
                        daysLeft = 25,
                        completedMissions = 3,
                        totalMissions = 30
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                // card misi harian
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    CardMission(
                        missionTitle = "Misi Harian",
                        missionDescription = "Kumpulin 10 botol plastik",
                        ptsReward = 100,
                        currentProgress = 8,
                        totalProgress = 10,
                        progressSuffix = "botol sudah terkumpul"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // produk menarik buat kamu
                Text(
                    text = "Produk Menarik Buat Kamu",
                    style = Typography.headlineLarge,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // semua produk
                val products = listOf(
                    Product(
                        "Jeans Totebag",
                        ProductCategory.FASHION,
                        "Rp25.000",
                        R.drawable.product_totebag
                    ),
                    Product(
                        "Pot Bunga Hewan",
                        ProductCategory.VASE,
                        "Rp20.000",
                        R.drawable.product_pot
                    ),
                    Product(
                        "Lampu Sendok",
                        ProductCategory.CRAFT,
                        "Rp15.000",
                        R.drawable.product_lampu
                    )
                )

                // looping list produk
                LazyRow(
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
                ) {
                    items(products) { product ->
                        CardProduct(
                            productImageResId = product.imageRes,
                            productName = product.name,
                            productCategory = product.category.toString(),
                            productPrice = product.price,
                            modifier = Modifier
                                .width(135.dp)
                                .height(175.dp),
                            imageHeight = 100,
                            onClick = { },
                            onAddToCart = { }
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            BottomNavigation(navController = navController)
        }

        // Bottom sheet exchange
        if (isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        isSheetOpen = false
                        currentStep = 1
                    }
                },
                sheetState = sheetState,
                dragHandle = null
            ) {
                BtmSheetExchange(
                    currentStep = currentStep,
                    points = "2540",
                    selectedBank = "",
                    amount = "Rp25.400,00",
                    adminFee = "Rp2.500,00",
                    totalAmount = "Rp27.900,00",
                    onPointsChange = { },
                    onBankSelected = { },
                    onExchangeClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            isSheetOpen = false
                            currentStep = 1
                            showPopup = true
                        }
                    },
                    onNext = { currentStep = 2 },
                    onDismiss = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            isSheetOpen = false
                            currentStep = 1
                            showPopup = true
                        }
                    }
                )
            }
        }

        // tampilkan pop up
        if (showPopup) {
            PopUpNotif(
                iconResId = R.drawable.ic_success,
                message = "Permintaan tukar poin berhasil!",
                buttonText = "Tutup",
                navController = navController,
                destination = null,
                onDismiss = { showPopup = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
