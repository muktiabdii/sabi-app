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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TextNominal(
    value: Int?,
    placeholder: String = ""
) {
    val formattedValue = value?.let {
        NumberFormat.getNumberInstance(Locale.US).format(it)
    } ?: placeholder

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .border(1.dp, BrownMain, RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(BrownMain, RoundedCornerShape(8.dp))
                .fillMaxHeight()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Rp",
                style = Typography.headlineSmall.copy(color = Color.White)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        // output nominal
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)
        ) {
            Text(
                text = formattedValue,
                style = Typography.bodyLarge.copy(color = if (value == null) GreyMedium else Color.Black)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextDisplayNominal() {
    TextNominal(value = 100000)
