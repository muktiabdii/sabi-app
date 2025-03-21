package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun ButtonAuth(
    text: String,
    backgroundColor: Color = BrownMain,
    textColor: Color = Color.White,
    borderColor: Color? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(size = 30.dp))
            .then(
                if (borderColor != null) Modifier.border(1.dp, borderColor, RoundedCornerShape(30.dp))
                else Modifier
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = Typography.headlineSmall.copy(color = textColor)
        )
    }
}


//@Preview
//@Composable
//fun PreviewButtonAuth() {
//    ButtonAuth(text = "MASUK SEBAGAI ADMIN", onClick = {})
//}
