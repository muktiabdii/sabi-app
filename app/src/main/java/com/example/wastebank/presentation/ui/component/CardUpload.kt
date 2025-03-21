package com.example.wastebank.presentation.ui.component

import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun CardUpload(
    text: String
) {
    val context = LocalContext.current

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
                    text = text,
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
}

@Preview(showBackground = true)
@Composable
fun PreviewCardUpload(modifier: Modifier = Modifier) {
    CardUpload(text = "Upload Foto Sampah di Sini")
}