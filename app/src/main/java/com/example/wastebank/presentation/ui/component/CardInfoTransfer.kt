package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import com.example.wastebank.domain.model.DonationDomain
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.Typography

// Informasi Transfer
@Composable
fun CardInfoTransfer(
    donation : DonationDomain?,
    collected: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(20.dp))
            .background(color = GreenBg, shape = RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = donation?.bank.orEmpty(),
                style = Typography.bodyMedium,
                color = Color.Black
            )
            Text(
                text = donation?.accountNumber.orEmpty(),
                style = Typography.bodyMedium,
                color = Color.Black
            )
            Text(
                text = donation?.accountName.orEmpty(),
                style = Typography.bodyMedium,
                color = Color.Black
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color.Black
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Donasi:",
                    style = Typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "Rp %,d".format(collected),
                    style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.Black,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

//@Preview(showBackground = false)
//@Composable
//fun PreviewCardInfoTransfer() {
//    CardInfoTransfer(
//        bank = "BCA",
//        accountNo = "1234567890",
//        name = "Yayasan Papuan Dengan Kita",
//        total = 50000
//    )
//}
