package com.example.wastebank.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonAuth
import com.example.wastebank.presentation.ui.component.CardSchedule
import com.example.wastebank.presentation.ui.component.TextFieldAuth
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun InputTrashScreen(navController: NavController? = null) {
    var email by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedTrashType by remember { mutableStateOf("") }

    val trashTypes = listOf("Plastik", "Kardus", "Kayu", "Kaca", "Bahan", "Lainnya")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(Modifier.height(30.dp))

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(16.dp)
                    .clickable { navController?.popBackStack() }
            )
            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Input Sampah",
                style = Typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Informasi Sampah
            Text(
                text = "Informasi Sampah",
                style = Typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            Text(
                text = "Email",
                style = Typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextFieldAuth(
                value = email,
                onValueChange = { email = it },
                placeholder = "Masukkan email"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Jenis Sampah
            Text(
                text = "Jenis Sampah",
                style = Typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Pilihan Sampah
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(trashTypes) { type ->
                    CardSchedule(
                        content = type,
                        corner = 20,
                        backgroundColor = Color.White,
                        isSelected = selectedTrashType == type,
                        onClick = { selectedTrashType = type }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Jumlah
            Text(
                text = "Jumlah",
                style = Typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextFieldAuth(
                value = amount,
                onValueChange = { amount = it },
                placeholder = "Masukkan jumlah",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number).copy(
                    imeAction = ImeAction.Done
                ),
            )
        }

        // Tombol Simpan Informasi
        ButtonAuth(
            text = "SIMPAN INFORMASI",
            backgroundColor = BrownMain,
            textColor = Color.White,
            onClick = { }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInputTrashScreen() {
    InputTrashScreen()
}
