package com.example.wastebank.presentation.ui.component

import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.*

@Composable
fun DialogUpload(
    subtotal: Int,
    pengiriman: Int,
    onDismiss: () -> Unit,
    onUploadClick: (Uri?) -> Unit
) {
    val context = LocalContext.current
    val total = subtotal + pengiriman

    // Menyimpan file yang dipilih
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var selectedFileName by remember { mutableStateOf("Ketuk untuk Upload") }

    // Launcher untuk memilih file
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri = uri
        uri?.let {
            // Mendapatkan nama file dari URI
            context.contentResolver.query(it, null, null, null, null)?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (cursor.moveToFirst() && nameIndex != -1) {
                    selectedFileName = cursor.getString(nameIndex)
                }
            }
        }
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .width(276.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
        ) {
            Column {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = BrownMain,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // Upload Bukti Transfer
                    Text(
                        text = "Upload Bukti Transfer",
                        style = Typography.headlineMedium.copy(color = Color.White),
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { onDismiss() }
                    )
                }

                Column(
                    modifier = Modifier.padding(20.dp),
                ) {
                    // Detail Pembayaran
                    Text(
                        text = "Detail Pembayaran",
                        style = Typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = GreenBg, shape = RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            DetailRow(label = "Subtotal", value = subtotal)
                            DetailRow(label = "Pengiriman", value = pengiriman)
                            DetailRow(label = "Total", value = total)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Upload Foto Bukti Transfer
                    Text(
                        text = "Upload Foto Bukti Transfer",
                        style = Typography.headlineSmall,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Box Upload
                    Box(
                        modifier = Modifier
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .height(168.dp)
                            .background(color = GreenBg, shape = RoundedCornerShape(10.dp))
                            // jalankan file picker untuk memilih gambar
                            .clickable { filePickerLauncher.launch("image/*") },
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedFileUri != null) {
                            // jika file sudah dipilih, hanya tampilkan nama file
                            Text(
                                text = selectedFileName,
                                style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )
                        } else {
                            // jika belum ada file, tampilkan ikon dan teks instruksi
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_upload),
                                    contentDescription = "Upload",
                                    tint = Color.Black
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Ketuk untuk Upload",
                                    style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Format: JPG, PNG, PDF\nMaks. 5MB",
                                    style = Typography.bodyMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    // Button Upload
                    ButtonAuth(
                        text = "UPLOAD DAN KONFIRMASI",
                        backgroundColor = BrownMain,
                        textColor = Color.White,
                        onClick = { onUploadClick(selectedFileUri) }
                    )
                }
            }
        }
    }
}

// menampilkan detail pembayaran
@Composable
fun DetailRow(label: String, value: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = Typography.bodyMedium
        )
        Text(
            text = "Rp %,d".format(value),
            style = Typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewDialogUpload() {
    DialogUpload(
        subtotal = 70000,
        pengiriman = 0,
        onDismiss = {},
        onUploadClick = {})
}
