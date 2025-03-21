package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

// Model data untuk setiap baris dalam tabel
data class OrderItem(
    val number: Int,
    val user: String,
    val type: String,
    val status: String
)

// Warna status
val statusColors = mapOf(
    "Done" to Color(0xFFDFF2D8),
    "Process" to Color(0xFFFFF0C2),
    "To Do" to Color(0xFFF8D6D6)
)

@Composable
fun TableOrder(orderList: List<OrderItem>) {
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
                .padding(12.dp)
        ) {
            TableCell("No", Modifier.weight(0.4f))
            TableCell("User", Modifier.weight(0.8f))
            TableCell("Tipe", Modifier.weight(0.8f))
            TableCell("Status", Modifier.weight(1f))
            TableCell("Detail", Modifier.weight(0.7f))
        }

        // Isi tabel
        orderList.forEachIndexed { index, order ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (index != orderList.lastIndex) {
                            Modifier.border(width = 0.5.dp, color = BrownMain)
                        } else {
                            Modifier
                        }
                    )
                    .padding(vertical = 12.dp, horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableCell("${order.number}.", Modifier.weight(0.4f))
                TableCell(order.user, Modifier.weight(0.8f))
                TableCell(order.type, Modifier.weight(0.8f))
                StatusCell(order.status, Modifier.weight(1f))
                DetailCell("Detail", Modifier.weight(0.7f))
            }
        }
    }
}

@Composable
fun StatusCell(status: String, modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(65.dp)
                .height(25.dp)
                .background(statusColors[status] ?: Color.Gray, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = status,
                style = Typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTableOrder() {
    val sampleOrders = listOf(
        OrderItem(1, "Tobby", "Produk", "Done"),
        OrderItem(2, "Fajar", "Donasi", "Process"),
        OrderItem(3, "Abdi", "Produk", "To Do")
    )

    TableOrder(orderList = sampleOrders)
}
