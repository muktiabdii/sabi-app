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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyLight
import com.example.wastebank.presentation.ui.theme.GreyLine
import com.example.wastebank.presentation.ui.theme.TextRed
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun CardDropPoint(
    name: String,
    distance: Double,
    location: String,
    capacity: Int,
    isOpen: Boolean
) {
    Row(
        modifier = Modifier
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(115.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 40.dp)
        ) {
            // nama tps
            Text(text = name, style = Typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(4.dp))

            // jarak
            Text(text = "Jarak: $distance km", style = Typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))

            // keterangan lokasi
            Text(text = location, style = Typography.bodyMedium, color = BrownMain)
            Spacer(modifier = Modifier.height(6.dp))

            // progress bar kapasitas
            val progressFraction = capacity / 100f
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(GreyLight, shape = RoundedCornerShape(10.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progressFraction)
                        .height(8.dp)
                        .background(
                            if (capacity < 80) BrownMain else TextRed,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Kapasitas $capacity%",
                style = Typography.bodyMedium,
                color = BrownMain
            ) // Kapasitas
        }

        // status button (buka/tutup)
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(25.dp)
                .background(
                    color = if (isOpen) BrownMain else TextRed,
                    shape = RoundedCornerShape(size = 15.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isOpen) "Buka" else "Tutup",
                style = Typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardDropPointPreview() {
    Column {
        CardDropPoint(
            name = "TPS 3R Gubeng",
            distance = 1.2,
            location = "Rumah Kompos UB",
            capacity = 45,
            isOpen = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        CardDropPoint(
            name = "Bank Sampah Sejahtera",
            distance = 1.8,
            location = "Rumah Kompos UB",
            capacity = 90,
            isOpen = false
        )
    }
}
