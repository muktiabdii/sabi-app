package com.example.wastebank.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.wastebank.domain.model.ProductCategory
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.YellowMain
import com.example.wastebank.presentation.viewmodel.AuthViewModel
import com.example.wastebank.presentation.viewmodel.MoneyExchangeViewModel
import com.example.wastebank.presentation.viewmodel.ProductViewModel
import com.example.wastebank.presentation.viewmodel.UserProfileViewModel
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userProfileViewModel: UserProfileViewModel,
    moneyExchangeViewModel: MoneyExchangeViewModel,
    authViewModel: AuthViewModel,
    productViewModel: ProductViewModel
) {
    // state user profile
    val userProfile by userProfileViewModel.userProfile.collectAsState()

    // state money exchange
    val points by moneyExchangeViewModel.points.collectAsState()
    val amount by moneyExchangeViewModel.amount.collectAsState()
    val selectedBank by moneyExchangeViewModel.bankName.collectAsState()
    val accountNumber by moneyExchangeViewModel.accountNumber.collectAsState()
    val errorMessageMoneyExchange by moneyExchangeViewModel.errorMessage.collectAsState()

    // state auth
    val password by authViewModel.password.collectAsState()

    // state product
    val products by productViewModel.products.collectAsState()

    // kelola state bottom sheet tukar poin
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isSheetOpen by remember { mutableStateOf(false) }
    var currentStep by remember { mutableStateOf(1) }
    val coroutineScope = rememberCoroutineScope()

    // state pop up
    var showPopup by remember { mutableStateOf(false) }
    var errorPopupPoint by remember { mutableStateOf(false) }
    var errorPopupPassword by remember { mutableStateOf(false) }

    LaunchedEffect(isSheetOpen) {
        if (isSheetOpen && !sheetState.isVisible) {
            coroutineScope.launch {
                sheetState.show()
            }
        }
    }

    LaunchedEffect(Unit) {
        userProfileViewModel.getUserProfile()
        productViewModel.getProducts()
        productViewModel.getCartItems()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(YellowMain)
            .verticalScroll(rememberScrollState())
    ) {
        // topbar
        TopBar(
            username = userProfile?.name.orEmpty(),
            points = userProfile?.points ?: 0,
            role = "user"
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
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
                        points = userProfile?.points ?: 0,
                        onViewPointsClick = { },
                        onRedeemPointsClick = { isSheetOpen = true } // buka bottom sheet
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))

                BannerDonation(navController)
                Spacer(modifier = Modifier.height(16.dp))

                // misi kamu hari ini
                Text(
                    text = "Misi Kamu Hari Ini",
                    style = Typography.headlineLarge,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                // card kampanye bulan maret
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    CardCampaign(
                        monthName = "Maret",
                        daysLeft = 25,
                        completedMissions = 3,
                        totalMissions = 30
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

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
                Spacer(modifier = Modifier.height(12.dp))

                // looping list produk
                LazyRow(
                    contentPadding = PaddingValues(start = 20.dp, end = 10.dp)
                ) {
                    items(products) { product ->
                        CardProduct(
                            product = product,
                            modifier = Modifier
                                .width(160.dp)
                                .height(230.dp),
                            imageHeight = 110,
                            onClick = {
                                val encodedName = URLEncoder.encode(
                                    product.name,
                                    StandardCharsets.UTF_8.toString()
                                )
                                navController.navigate("product_detail_screen/$encodedName")
                            },
                            onAddToCart = { product?.let { productViewModel.addToCart(it) } }
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
                Spacer(modifier = Modifier.height(90.dp))
            }
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
                    moneyExchangeViewModel,
                    authViewModel,
                    points,
                    amount,
                    selectedBank,
                    accountNumber,
                    password,
                    currentStep = currentStep,
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
                            authViewModel.checkPassword { success, message ->
                                if (success) {
                                    moneyExchangeViewModel.exchangeMoney { success, message ->
                                        if (success) {
                                            showPopup = true
                                        } else {
                                            showPopup = true
                                            errorPopupPoint = true
                                        }
                                    }

                                } else {
                                    showPopup = true
                                    errorPopupPassword = true
                                }
                            }
                        }
                    }
                )
            }
        }

        // tampilkan pop up
        if (showPopup) {
            PopUpNotif(
                iconResId = if (errorPopupPoint || errorPopupPassword) R.drawable.ic_alert else R.drawable.ic_success,
                message = when {
                    errorPopupPoint -> errorMessageMoneyExchange
                        ?: "Maaf, poin Anda tidak mencukupi" // Pesan error terkait poin
                    errorPopupPassword -> errorMessageMoneyExchange
                        ?: "Maaf, password Anda salah" // Pesan error password
                    else -> "Permintaan tukar poin berhasil!" // Pesan sukses
                },
                buttonText = "Tutup",
                navController = navController,
                destination = null,
                onDismiss = {
                    showPopup = false
                    errorPopupPoint = false
                    errorPopupPassword = false
                    moneyExchangeViewModel.resetErrorMessage()
                    userProfileViewModel.getUserProfile()
                    moneyExchangeViewModel.clearState()
                    authViewModel.clearPasswordInput()
                }
            )
        }
        // jika password salah
//        PopUpNotif(
//            iconResId = R.drawable.ic_alert,
//            message = "Maaf, password yang dimasukkan salah!",
//            buttonText = "Tutup",
//            navController = navController,
//            destination = null,
//            onDismiss = { showPopup = false }
//        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewHomeScreen() {
//    val navController = rememberNavController()
//    HomeScreen(navController = navController)
//}
