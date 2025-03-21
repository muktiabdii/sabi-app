package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun CardNominal(
    nominal: Int,
    type: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // menyesuaikan format berdasarkan tipe
    val displayText = when (type) {
        "transfer" -> "Rp %,d".format(nominal)
        "point" -> (nominal / 10).toString()
        else -> nominal.toString()
    }

    Box(
        modifier = Modifier
            .width(115.dp)
            .height(45.dp)
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(10.dp))
            .background(
                color = if (isSelected) BrownMain else Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = displayText,
            style = Typography.headlineSmall.copy(
                color = if (isSelected) Color.White else Color.Black,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardNominalTransfer() {
    CardNominal(nominal = 10000, type = "transfer", isSelected = false, onClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewCardNominalPoint() {
    CardNominal(nominal = 10000, type = "point", isSelected = true, onClick = {})
}
