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
import com.example.wastebank.presentation.ui.theme.Typography

// Model data untuk setiap baris dalam tabel Pickup
data class PickupItem(
    val user: String,
    val address: String,
    val date: String
)

@Composable
fun TablePickup(pickupList: List<PickupItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BrownMain, RoundedCornerShape(10.dp))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 4.dp)
        ) {
            TableCell("User", Modifier.weight(0.8f))
            TableCell("Alamat", Modifier.weight(1f))
            TableCell("Tanggal", Modifier.weight(1f))
            TableCell("Detail", Modifier.weight(0.8f))
        }

        // Isi tabel
        pickupList.forEachIndexed { index, pickup ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (index != pickupList.lastIndex) {
                            Modifier.border(width = 0.5.dp, color = BrownMain)
                        } else {
                            Modifier
                        }
                    )
                    .padding(vertical = 12.dp, horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableCell(pickup.user, Modifier.weight(0.8f))
                TableCell(pickup.address, Modifier.weight(1f))
                TableCell(pickup.date, Modifier.weight(1f))
                DetailCell("Detail", Modifier.weight(0.8f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTablePickup() {
    val samplePickupList = listOf(
        PickupItem("Tobby", "Lowokwaru", "14/03/2025"),
        PickupItem("Fajar", "Mojokerto", "14/03/2025"),
        PickupItem("Abdi", "Lowokwaru", "14/03/2025")
    )

    TablePickup(pickupList = samplePickupList)
}
