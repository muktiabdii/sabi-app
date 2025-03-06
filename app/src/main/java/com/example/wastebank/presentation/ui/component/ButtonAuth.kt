package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.TextWhite
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun ButtonAuth(
    text: String,
    backgroundColor: Color = BrownMain,
    textColor: Color = TextWhite,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(322.dp)
            .height(34.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(size = 5.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = Typography.headlineSmall.copy(color = textColor)
        )
    }
}

@Preview
@Composable
fun PreviewButtonAuth() {
    ButtonAuth(text = "MASUK SEBAGAI ADMIN", onClick = {})
}
