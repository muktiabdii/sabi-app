package com.example.wastebank.presentation.ui.component

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.R
import java.util.*

@Composable
fun TextFieldDate(
    value: String, // nilai tanggal yang dipilih
    onValueChange: (String) -> Unit, // callback untuk mengupdate tanggal
    placeholder: String = "DD/MM/YY" // placeholder default
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(1.dp, BrownMain, RoundedCornerShape(12.dp))
            .clickable { showDatePicker(context, onValueChange) } // buka DatePicker saat diklik
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // menampilkan tanggal yang dipilih atau placeholder jika belum ada
            Text(
                text = if (value.isNotEmpty()) value else placeholder,
                style = Typography.bodyLarge.copy(color = if (value.isNotEmpty()) Color.Black else GreyMedium),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )

            // icon kalender
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "Select Date",
                tint = GreyMedium,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}


// menampilkan DatePickerDialog Android
@SuppressLint("DefaultLocale")
private fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
    // menggunakan waktu saat ini sebagai default
    val calendar = Calendar.getInstance()

    val datePicker = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            // format hasil pilihan ke DD/MM/YY
            val formattedDate = String.format("%02d/%02d/%02d", dayOfMonth, month + 1, year % 100)
            onDateSelected(formattedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    // menampilkan dialog
    datePicker.show()
}

@Preview(showBackground = true)
@Composable
fun TextFieldDatePreview() {
    var selectedDate by remember { mutableStateOf("") }

    TextFieldDate(
        value = selectedDate,
        onValueChange = { selectedDate = it }
    )
}
