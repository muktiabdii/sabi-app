package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.GreenBg

// Bukti Transfer
@Composable
fun CardTransferSlip(
    subtotal: Int,
    shippingCost: Int
) {
    val total = subtotal + shippingCost
    var showDialogUpload by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(20.dp))
            .background(color = GreenBg, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Bukti Transfer",
                style = Typography.headlineLarge,
                color = Color.Black
            )
            Text(
                text = "Upload bukti Transfer Anda",
                style = Typography.bodyMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            ButtonAuth(
                text = "UPLOAD FOTO BUKTI TRANSFER",
                backgroundColor = BrownMain,
                textColor = Color.White,
                onClick = { showDialogUpload = true }
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }

    if (showDialogUpload) {
        DialogUpload(
            subtotal = subtotal,
            pengiriman = shippingCost,
            onDismiss = { showDialogUpload = false },
            onUploadClick = { showDialogUpload = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardTransferSlip() {
    CardTransferSlip(
        subtotal = 70000,
        shippingCost = 0
    )
}
