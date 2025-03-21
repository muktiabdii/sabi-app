package com.example.wastebank.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun CardRequest(
    name: String,
    address: String,
    dateTime: String,
    weight: String,
    status: String,
    modifier: Modifier = Modifier,
    onDetailClick: () -> Unit
) {
    // Warna status berdasarkan kategori
    val statusColor = when (status) {
        "Permintaan Baru" -> Color(0xFFDDEBFC) // Biru Muda
        "Process" -> Color(0xFFFCF3D4) // Kuning Muda
        "Done" -> Color(0xFFDFF0D8) // Hijau Muda
        else -> Color.LightGray
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, BrownMain, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Kolom untuk alamat, tanggal, dan berat sampah
            Column {
                Text(
                    text = name,
                    style = Typography.headlineMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = address,
                    style = Typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = dateTime,
                    style = Typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Berat: $weight kg",
                    style = Typography.bodyLarge,
                    color = Color.Black
                )
            }

            // Kolom untuk status dan tombol detail
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Box(
                    modifier = Modifier
                        .background(statusColor, shape = RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = status,
                        style = Typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(36.dp))

                // Button Detail tanpa padding bawaan
                Button(
                    onClick = onDetailClick,
                    colors = ButtonDefaults.buttonColors(containerColor = BrownMain),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .height(30.dp)
                        .width(70.dp)
                ) {
                    Text(
                        text = "Detail",
                        style = Typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardRequest() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        CardRequest(
            name = "Tobby",
            address = "Jl. Veteran",
            dateTime = "12 Maret 2025",
            weight = "2.5",
            status = "Permintaan Baru",
            onDetailClick = {}
        )
        CardRequest(
            name = "Fajar",
            address = "Jl. MT. Haryono",
            dateTime = "12 Maret 2025",
            weight = "5",
            status = "Process",
            onDetailClick = {}
        )
        CardRequest(
            name = "Abdi",
            address = "Jl. Sigura-Gura",
            dateTime = "11 Maret 2025",
            weight = "4",
            status = "Done",
            onDetailClick = {}
        )
    }
}
