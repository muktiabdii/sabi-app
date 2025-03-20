package com.example.wastebank.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastebank.presentation.ui.theme.BrownMain

@Composable
fun RadioButtonPayment(label: String, selected: String, onSelect: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .clickable { onSelect(label) },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, BrownMain)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )

                RadioButton(
                    selected = selected == label,
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = BrownMain,
                        unselectedColor = Color.Gray
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonPaymentPreview() {
    var selectedOption by remember { mutableStateOf("Transfer Bank") }

    Column(modifier = Modifier.padding(16.dp)) {
        RadioButtonPayment(label = "Transfer Bank", selected = selectedOption) {
            selectedOption = it
        }
        Spacer(modifier = Modifier.height(12.dp))
        RadioButtonPayment(label = "Gunakan Poin", selected = selectedOption) {
            selectedOption = it
        }
    }
}
