package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownBg
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun CardSchedule(
    content: String,
    corner: Int,
    backgroundColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(115.dp)
            .height(36.dp)
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(corner.dp))
            .background(
                color = if (isSelected) BrownMain else backgroundColor,
                shape = RoundedCornerShape(corner.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = content,
            style = Typography.headlineSmall.copy(
                color = if (isSelected) Color.White else Color.Black,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardSchedule() {
    Column {
        CardSchedule(
            content = "Plastik",
            corner = 20,
            backgroundColor = Color.White,
            isSelected = false,
            onClick = {})
        Spacer(modifier = Modifier.height(8.dp))
        CardSchedule(
            content = "09:00",
            corner = 12,
            backgroundColor = BrownBg,
            isSelected = false,
            onClick = {})
        Spacer(modifier = Modifier.height(8.dp))
        CardSchedule(
            content = "17:00",
            corner = 12,
            backgroundColor = BrownBg,
            isSelected = true,
            onClick = {})
    }
}
