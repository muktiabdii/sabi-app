package com.example.wastebank.presentation.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.presentation.ui.component.*
import com.example.wastebank.presentation.ui.theme.*
import com.example.wastebank.R
import com.example.wastebank.presentation.viewmodel.PickupViewModel
import com.example.wastebank.presentation.viewmodel.UploadcareViewModel

@Composable
fun RequestScreen(navController: NavController?, pickupViewModel: PickupViewModel, uploadcareViewModel: UploadcareViewModel) {
    val context = LocalContext.current

    val uploadResult by uploadcareViewModel.uploadResult.collectAsState()

    val pickupData by pickupViewModel.pickupData.collectAsState()
    var selectedTrashType by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    val trashTypes = listOf("Plastik", "Kardus", "Kayu", "Kaca", "Bahan", "Lainnya")
    val times = listOf("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00")

    var showPopUpNotif by remember { mutableStateOf(false) }

    LaunchedEffect(uploadResult) {
        uploadResult?.let {
            pickupViewModel.setProofImageUrl(it)
            Toast.makeText(context, "Upload Berhasil!", Toast.LENGTH_SHORT).show()
        }

    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(50.dp))

            // jadwal dan request
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                navController?.let { SwitchSchedule(it, defaultSelected = true) }
            }
            Spacer(modifier = Modifier.height(28.dp))
        }

        item {
            // alamat
            Text("Alamat", style = Typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldAuth(
                value = pickupData.address,
                onValueChange = { pickupViewModel.updatePickupData(pickupData.copy(address = it)) },
                placeholder = "Masukkan alamat"
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                // berat sampah
                Column(modifier = Modifier.weight(1f)) {
                    Text("Berat Sampah (kg)", style = Typography.headlineMedium)
                    Spacer(modifier = Modifier.height(10.dp))

                    TextFieldAuth(
                        value = pickupData.weight,
                        onValueChange = {
                            if (it.isEmpty() || it.toDoubleOrNull() != null) pickupViewModel.updatePickupData(pickupData.copy(weight = it))
                        },
                        placeholder = "0",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))

                // no telepon
                Column(modifier = Modifier.weight(1f)) {
                    Text("No. Telepon", style = Typography.headlineMedium)
                    Spacer(modifier = Modifier.height(10.dp))

                    TextFieldAuth(
                        value = pickupData.phoneNumber,
                        onValueChange = {
                            val regex = Regex("^\\+?\\d{0,15}$") // hanya input angka
                            if (regex.matches(it)) {
                                pickupViewModel.updatePickupData(pickupData.copy(phoneNumber = it))
                            }
                        },
                        placeholder = "Masukkan No. HP",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // tanggal penjemputan
        item {
            Text("Tanggal Penjemputan", style = Typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldDate(
                value = pickupData.selectedDate,
                onValueChange = { pickupViewModel.updatePickupData(pickupData.copy(selectedDate = it)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // jenis sampah
        item {
            Text("Jenis Sampah", style = Typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))
        }

        // pilihan sampah
        item {
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
                        onClick = { pickupViewModel.updatePickupData(pickupData.copy(trashType = type)) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // pilih waktu
        item {
            Text("Pilih Waktu", style = Typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))
        }

        // pilihan waktu
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(times) { time ->
                    CardSchedule(
                        content = time,
                        corner = 10,
                        backgroundColor = BrownBg,
                        isSelected = selectedTime == time,
                        onClick = { pickupViewModel.updatePickupData(pickupData.copy(selectedTime = time)) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // foto sampah
        item {
            Text("Foto Sampah", style = Typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))

            // upload foto sampah
            CardUpload(uploadcareViewModel)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text("Keterangan Lokasi Sampah", style = Typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))

            TextFieldDescription(
                value = pickupData.description,
                onValueChange = { pickupViewModel.updatePickupData(pickupData.copy(description = it)) },
                placeholder = "Contoh: Di depan pagar, samping taman, dekat tong hijau, dll."
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            ButtonAuth(
                text = "KIRIM PERMINTAAN",
                onClick = {
                    val proofUrl = pickupViewModel.proofImageUrl.value
                    if (proofUrl.isNullOrEmpty()) {
                        Toast.makeText(
                            context,
                            "Harap upload bukti transfer terlebih dahulu!",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@ButtonAuth // Hentikan eksekusi lebih lanjut
                    } else {
                        pickupViewModel.requestPickup(pickupData)
                        showPopUpNotif = true
                    }
                }
            )
            Spacer(modifier = Modifier.height(90.dp))
        }
    }

    // PopUp Notifikasi Permintaan Terkirim
    if (showPopUpNotif) {
        PopUpNotif(
            iconResId = R.drawable.ic_success,
            message = "Permintaan Terkirim!",
            buttonText = "KEMBALI",
            navController = navController,
            onDismiss = {
                showPopUpNotif = false
                navController?.navigate("maps_screen") {
                    popUpTo("request_screen") { inclusive = true }
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewRequestScreen() {
//    RequestScreen(navController = rememberNavController())
//}
