package com.example.wastebank.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.presentation.component.CardRequest
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun RequestScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 36.dp, bottom = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Kelola Permintaan",
                    style = Typography.headlineLarge,
                    color = Color.Black
                )
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Tambah Permintaan",
                        tint = BrownMain
                    )
                }
            }
        }

        // daftar permintaan
        items(requestList) { request ->
            Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
                CardRequest(
                    name = request.name,
                    address = request.address,
                    dateTime = request.dateTime,
                    weight = request.weight,
                    status = request.status,
                    onDetailClick = { }
                )
            }
        }
    }
}

data class RequestData(
    val name: String,
    val address: String,
    val dateTime: String,
    val weight: String,
    val status: String
)

val requestList = listOf(
    RequestData("Tobby", "Jl. Veteran, Malang", "12 Maret 2025, 14:00", "2.5", "Permintaan Baru"),
    RequestData("Fajar", "Jl. MT. Haryono, Malang", "12 Maret 2025, 12:00", "5", "Process"),
    RequestData("Abdi", "Jl. Sigura-Gura, Malang", "11 Maret 2025, 10:00", "4", "Done")
)

@Preview(showBackground = true)
@Composable
fun PreviewRequestScreen() {
    RequestScreen()
}
