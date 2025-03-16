package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun DonationDetailScreen(navController: NavController) {
    var selectedNominal by remember { mutableStateOf<Int?>(null) }
    var customNominal by remember { mutableStateOf("") }

    // state untuk menampilkan dialog upload
    var showDialogUpload by remember { mutableStateOf(false) }
    var showPopUpNotif by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
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
        Spacer(modifier = Modifier.height(16.dp))

        CardDonationDetail(
            title = "Papua Dengan Kita",
            description = "Beri Subsidi untuk mereka yang berada di Timur!",
            percent = 67
        )
        Spacer(modifier = Modifier.height(24.dp))

        // pilih nominal donasi
        Text(
            text = "Pilih Nominal Donasi",
            style = Typography.headlineSmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        val nominalList = listOf(10000, 25000, 50000, 100000, 250000, 500000)

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(nominalList) { nominal ->
                CardNominal(
                    nominal = nominal,
                    isSelected = selectedNominal == nominal,
                    onClick = {
                        selectedNominal = nominal
                        customNominal = ""
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // input nominal lain
        Text(
            text = "Atau Masukkan Nominal Lain",
            style = Typography.headlineSmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldNominal(
            value = customNominal,
            onValueChange = {
                customNominal = it
                selectedNominal = null
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        // informasi transfer
        Text(
            text = "Informasi Transfer",
            style = Typography.headlineSmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        CardInfoTransfer(
            bank = "BCA",
            accountNo = "1234567890",
            name = "Yayasan Papua Dengan Kita",
            total = selectedNominal ?: customNominal.toIntOrNull() ?: 0
        )
        Spacer(modifier = Modifier.height(24.dp))

        // buttons
        ButtonAuth(
            text = "SALIN NOMOR REKENING",
            backgroundColor = Color.White,
            textColor = BrownMain,
            borderColor = BrownMain,
            onClick = { /* Handle Copy Account Number */ }
        )
        Spacer(modifier = Modifier.height(12.dp))

        ButtonAuth(
            text = "UPLOAD BUKTI TRANSFER",
            backgroundColor = BrownMain,
            textColor = Color.White,
            onClick = { showDialogUpload = true }
        )
    }

    // Dialog Upload Bukti Transfer
    if (showDialogUpload) {
        DialogUpload(
            subtotal = selectedNominal ?: customNominal.toIntOrNull() ?: 0,
            pengiriman = 0,
            onDismiss = { showDialogUpload = false },
            onUploadClick = { showPopUpNotif = true }
        )
    }

    // PopUp Notifikasi Pembayaran Berhasil
    if (showPopUpNotif) {
        PopUpNotif(
            iconResId = R.drawable.ic_success,
            message = "Pembayaran Berhasil!",
            buttonText = "KEMBALI MASUK",
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

@Preview(showBackground = true)
@Composable
fun PreviewDonationDetailScreen() {
    val navController = rememberNavController()
    DonationDetailScreen(navController)
}
