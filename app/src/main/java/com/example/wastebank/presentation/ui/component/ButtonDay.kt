package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun ButtonDay() {
    val days = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")

    // State untuk menyimpan index hari yang dipilih
    var selectedIndex by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(size = 10.dp))
            .background(color = BrownMain, shape = RoundedCornerShape(size = 10.dp))
            .padding(horizontal = 18.dp)
            .width(280.dp)
            .height(45.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // tombol kembali ke hari sebelumnya
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Previous Day",
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    if (selectedIndex > 0) {
                        selectedIndex--
                    } else {
                        selectedIndex = days.lastIndex
                    }
                },
            tint = Color.White
        )

        // teks nama hari yang dipilih
        Text(
            text = days[selectedIndex],
            style = Typography.headlineLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
            color = Color.White
        )

        // tombol maju ke hari berikutnya
        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Next Day",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    if (selectedIndex < days.lastIndex) {
                        selectedIndex++
                    } else {
                        selectedIndex = 0
                    }
                },
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonDay() {
    ButtonDay()
}
