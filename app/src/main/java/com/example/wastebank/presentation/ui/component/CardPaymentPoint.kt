package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.GreenBg

@Composable
fun CardPaymentPoint(
    availablePoints: Int,
    requiredPoints: Int,
    onConfirmClick: () -> Unit
) {
    val remainingPoints = availablePoints - requiredPoints

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
                text = "Gunakan Poin",
                style = Typography.headlineLarge,
            )
            Text(
                text = "Poin Tersedia: %,d".format(availablePoints),
                style = Typography.bodyMedium,
            )
            Text(
                text = "Poin Diperlukan: %,d".format(requiredPoints),
                style = Typography.bodyMedium,
            )
            Text(
                // tampilkan poin tidak cukup jika sisa poin < 0
                text = if (remainingPoints >= 0) "Poin Tersisa: %,d".format(remainingPoints) else "Poin tidak cukup",
                style = Typography.bodyMedium,
                color = if (remainingPoints < 0) Color.Red else Color.Unspecified
            )
            Spacer(modifier = Modifier.height(4.dp))
            ButtonAuth(
                text = "KONFIRMASI POIN",
                backgroundColor = BrownMain,
                textColor = Color.White,
                onClick = onConfirmClick
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardPaymentPoint() {
    Column {
        CardPaymentPoint(
            availablePoints = 10000,
            requiredPoints = 7000,
            onConfirmClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        CardPaymentPoint(
            availablePoints = 5000,
            requiredPoints = 7000,
            onConfirmClick = {}
        )
    }
}
