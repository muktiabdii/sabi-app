package com.example.wastebank.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.component.CardBankTransfer
import com.example.wastebank.presentation.ui.component.CardTransferSlip
import com.example.wastebank.presentation.ui.component.PopUpNotif
import com.example.wastebank.presentation.ui.component.PriceDetailRow
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.viewmodel.ProductViewModel
import com.example.wastebank.presentation.viewmodel.UploadcareViewModel

@Composable
fun PaymentScreen(
    navController: NavController,
    subtotal: Int,
    shippingCost: Int,
    total: Int,
    productViewModel: ProductViewModel,
    uploadcareViewModel: UploadcareViewModel
) {
    var showPopUpNotif by remember { mutableStateOf(false) }

    val uploadResult by uploadcareViewModel.uploadResult.collectAsState()

    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        productViewModel.getCartItems()
    }

    LaunchedEffect(uploadResult) {
        uploadResult?.let { productViewModel.setProofImageUrl(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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

                // Pembayaran
                Text(
                    text = "Pembayaran",
                    style = Typography.headlineLarge,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Opsi Pembayaran
            Text(
                text = "Opsi Pembayaran yang Direkomendasikan",
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Metode Transfer Bank
            CardBankTransfer(
                bank = "BCA",
                accountNo = "9384759381",
                name = "SABI Official",
                total = total,
                onCopyClick = {
                    val accountNo = "9384759381"
                    clipboardManager.setText(AnnotatedString(accountNo))
                        Toast.makeText(context, "Nomor rekening disalin!", Toast.LENGTH_SHORT)
                            .show()
                    }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Upload Bukti Transfer
            CardTransferSlip(subtotal = subtotal, shippingCost = shippingCost, uploadcareViewModel)

            Spacer(modifier = Modifier.height(20.dp))

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

        // Tombol Bayar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            ButtonAuth(
                text = "BAYAR",
                backgroundColor = BrownMain,
                textColor = Color.White,
                onClick = {
                    val proofUrl = productViewModel.proofImageUrl.value
                    if (proofUrl.isNullOrEmpty()) {
                        Toast.makeText(context, "Harap upload bukti transfer terlebih dahulu!", Toast.LENGTH_SHORT).show()
                    } else {
                        productViewModel.payment()
                        showPopUpNotif = true
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }

    // PopUp Notifikasi Pembayaran Berhasil
    if (showPopUpNotif) {
        PopUpNotif(
            iconResId = R.drawable.ic_success,
            message = "Pembayaran Berhasil",
            buttonText = "KEMBALI",
            navController = navController,
            onDismiss = {
                // tutup pop up notif
                showPopUpNotif = false
                // kembali ke halaman marketplace
                navController.navigate("marketplace_screen") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewPaymentScreen() {
//    PaymentScreen(navController = rememberNavController())
//}
