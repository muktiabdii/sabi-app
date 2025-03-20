package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun PriceDetailRow(label: String, amount: Int, type: String) {
    // format berdasarkan rupiah atau poin
    val formattedAmount = when (type) {
        "transfer" -> "Rp %,d".format(amount)
        "point" -> "%,d pts".format(amount / 10)
        else -> amount.toString()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = Typography.bodyLarge
        )
        Text(
            text = formattedAmount,
            style = Typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}