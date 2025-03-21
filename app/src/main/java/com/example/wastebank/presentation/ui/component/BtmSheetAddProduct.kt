package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.viewmodel.UploadcareViewModel

@Composable
fun BtmSheetAddProduct(
    uploadcareViewModel: UploadcareViewModel,
    categoryList: List<String>,
    onAddProduct: () -> Unit
) {
    // State untuk menyimpan input
    var productName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(750.dp)
            .background(Color.White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Garis atas indikator
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(5.dp)
                    .background(BrownMain, RoundedCornerShape(2.dp))
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Judul "Tambah Produk"
            Text(text = "Tambah Produk", style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            // Informasi Produk
            Text(text = "Informasi Produk", style = Typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Nama Produk
            Text(text = "Nama Produk", style = Typography.headlineSmall)
            Spacer(modifier = Modifier.height(6.dp))
            TextFieldAuth(
                value = productName,
                onValueChange = { productName = it },
                placeholder = "Masukkan nama produk"
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Kategori
            Text(text = "Kategori", style = Typography.headlineSmall)
            Spacer(modifier = Modifier.height(6.dp))
            DropdownCategory(
                categoryList = categoryList,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Harga
            Text(text = "Harga", style = Typography.headlineSmall)
            Spacer(modifier = Modifier.height(6.dp))
            TextFieldNominal(value = price, onValueChange = { price = it }, placeholder = "0")
            Spacer(modifier = Modifier.height(12.dp))

            // Foto Produk
            Text(text = "Foto Produk", style = Typography.headlineSmall)
            Spacer(modifier = Modifier.height(6.dp))
            CardUpload(uploadcareViewModel)
            Spacer(modifier = Modifier.height(12.dp))

            // Deskripsi Produk
            Text(text = "Deskripsi Produk", style = Typography.headlineSmall)
            Spacer(modifier = Modifier.height(6.dp))
            TextFieldDescription(
                value = description,
                onValueChange = { description = it },
                placeholder = "Masukkan deskripsi produk"
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Simpan Produk
            ButtonAuth(
                text = "SIMPAN PRODUK",
                onClick = { }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewBtmSheetAddProduct() {
//    BtmSheetAddProduct(
//        categoryList = listOf("Vas Bunga", "Fashion", "Prakarya", "Mainan", "Dekorasi"),
//        onAddProduct = { }
//    )
//}
