package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun TextFieldDescription(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = GreenBg)
            .border(1.dp, BrownMain, RoundedCornerShape(8.dp))
            .padding(16.dp),
    ) {
        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            textStyle = Typography.bodyLarge.copy(color = Color.Black),
            modifier = Modifier.fillMaxSize(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxHeight(),
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = Typography.bodyLarge.copy(color = GreyMedium),
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextFieldDescription() {
    TextFieldDescription(
        value = "",
        onValueChange = {},
        placeholder = "Contoh : Di depan pagar, samping taman, dekat tong hijau, dll."
    )
}
