package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium

@Composable
fun RadioButtonGender(label: String, selected: String, onSelect: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected == label,
            onClick = { onSelect(label) },
            colors = RadioButtonDefaults.colors(
                selectedColor = BrownMain,
                unselectedColor = GreyMedium
            )
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}