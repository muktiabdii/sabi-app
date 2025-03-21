package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.GreenBg

// Transfer Bank
@Composable
fun CardBankTransfer(
    bank: String,
    accountNo: String,
    name: String,
    total: Int,
    onCopyClick: () -> Unit
) {
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
                text = "Transfer Bank",
                style = Typography.headlineLarge,
            )
            Text(
                text = "Bank: $bank",
                style = Typography.bodyMedium,
            )
            Text(
                text = "No. Rekening: $accountNo",
                style = Typography.bodyMedium,
            )
            Text(
                text = "Atas Nama: $name",
                style = Typography.bodyMedium,
            )
            Text(
                text = "Jumlah Transfer: Rp %,d".format(total),
                style = Typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            ButtonAuth(
                text = "SALIN NOMOR REKENING",
                backgroundColor = BrownMain,
                textColor = Color.White,
                onClick = onCopyClick
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardBankTransfer() {
    CardBankTransfer(
        bank = "BCA",
        accountNo = "1234567890",
        name = "SABI Official",
        total = 70000,
        onCopyClick = {}
    )
}
