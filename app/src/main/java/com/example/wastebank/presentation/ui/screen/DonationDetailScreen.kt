package com.example.wastebank.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.components.RadioButtonPayment
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.viewmodel.DonationViewModel
import com.example.wastebank.presentation.viewmodel.ProductViewModel
import com.example.wastebank.presentation.viewmodel.UploadcareViewModel
import com.example.wastebank.presentation.viewmodel.UserProfileViewModel

@Composable
fun DonationDetailScreen(
    navController: NavController,
    donationViewModel: DonationViewModel,
    uploadcareViewModel: UploadcareViewModel,
    userProfileViewModel: UserProfileViewModel
) {
    var selectedOption by remember { mutableStateOf("Transfer Bank") }

    var selectedNominal by remember { mutableStateOf<Int?>(null) }
    var customNominal by remember { mutableStateOf("") }
    val selectedDonation by donationViewModel.selectedDonation.collectAsState()

    var showDialogUpload by remember { mutableStateOf(false) }
    var showPopUpNotif by remember { mutableStateOf(false) }

    val userProfile by userProfileViewModel.userProfile.collectAsState()
    val availablePoints = userProfile?.points ?: 0
    var customPoint by remember { mutableStateOf("") }

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val nominalList = listOf(10000, 25000, 50000, 100000, 250000, 500000)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(30.dp))

            // judul dengan tombol back
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Detail Donasi",
                    style = Typography.headlineSmall.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        item {
            // pilih metode pembayaran
            Text(
                text = "Pilih Metode Pembayaran",
                style = Typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(10.dp))

            // radio button metode
            Column {
                RadioButtonPayment(label = "Transfer Bank", selected = selectedOption) {
                    selectedOption = it
                }
                Spacer(modifier = Modifier.height(12.dp))
                RadioButtonPayment(label = "Gunakan Poin", selected = selectedOption) {
                    selectedOption = it
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
        }

        item {
            // card detail donasi
            CardDonationDetail(donation = selectedDonation)
        }

        item {
            // pilih nominal
            Text(
                text = "Pilih Nominal Donasi",
                style = Typography.headlineSmall.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        // bagian yang berubah sesuai metode donasi
        if (selectedOption == "Transfer Bank") {
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 500.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // tampilkan pilihan nominal
                    items(nominalList) { nominal ->
                        CardNominal(
                            nominal = nominal,
                            type = "transfer",
                            isSelected = selectedNominal == nominal,
                            onClick = {
                                selectedNominal = nominal
                                customNominal = ""
                            }
                        )
                    }
                }
            }

            item {
                // masukkan nominal lain
                Text(
                    text = "Atau Masukkan Nominal Lain",
                    style = Typography.headlineSmall.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            item {
                // input nominal lain
                TextFieldNominal(
                    value = customNominal,
                    onValueChange = {
                        customNominal = it
                        selectedNominal = null
                    }
                )
            }

            item {
                // informasi transfer
                Text(
                    text = "Informasi Transfer",
                    style = Typography.headlineSmall.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            item {
                CardInfoTransfer(
                    donation = selectedDonation,
                    totalAmount = selectedNominal ?: customNominal.toIntOrNull() ?: 0
                )
            }

            item {
                // button salin nomor rekening
                ButtonAuth(
                    text = "SALIN NOMOR REKENING",
                    backgroundColor = Color.White,
                    textColor = BrownMain,
                    borderColor = BrownMain,
                    onClick = {
                        val accountNumber = selectedDonation?.accountNumber.orEmpty()
                        if (accountNumber.isNotEmpty()) {
                            clipboardManager.setText(AnnotatedString(accountNumber))
                            Toast.makeText(context, "Nomor rekening disalin!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )
            }

            item {
                // button upload bukti transfer
                ButtonAuth(
                    text = "UPLOAD BUKTI TRANSFER",
                    backgroundColor = Color.White,
                    textColor = BrownMain,
                    borderColor = BrownMain,
                    onClick = { showDialogUpload = true }
                )
            }

        } else {
            // jika menggunakan metode poin
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 500.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // tampilkan pilihan nominal
                    items(nominalList) { nominal ->
                        CardNominal(
                            nominal = nominal,
                            type = "point",
                            isSelected = selectedNominal == nominal,
                            onClick = {
                                selectedNominal = nominal
                                customNominal = ""
                            }
                        )
                    }
                }
            }

            item {
                // masukkan nominal lain
                Text(
                    text = "Atau Masukkan Nominal Lain",
                    style = Typography.headlineSmall.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            item {
                // card poin tersedia
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp)
                        .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(20.dp))
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Poin Tersedia: $availablePoints",
                            style = Typography.bodyMedium,
                        )
                        Text(
                            text = "1 poin = Rp 10",
                            style = Typography.bodyMedium,
                        )
                    }

                    // input nominal point
                    TextFieldPoint(
                        value = customPoint,
                        onValueChange = { customPoint = it }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            // button bayar
            ButtonAuth(
                text = "BAYAR",
                backgroundColor = BrownMain,
                textColor = Color.White,
                onClick = { showPopUpNotif = true }
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

    // dialog upload bukti transfer
    if (showDialogUpload) {
        DialogUpload(
            subtotal = selectedNominal ?: customNominal.toIntOrNull() ?: 0,
            pengiriman = 0,
            onDismiss = { showDialogUpload = false },
            uploadcareViewModel = uploadcareViewModel
        )
    }

    // pop up notifikasi pembayaran akan diproses
    if (showPopUpNotif) {
        PopUpNotif(
            iconResId = R.drawable.ic_success,
            message = "Pembayaran akan Diproses!",
            buttonText = "KEMBALI",
            navController = navController,
            onDismiss = {
                showPopUpNotif = false
                showDialogUpload = false
                navController.navigate("donate_screen") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                }
            }
        )
    }
}