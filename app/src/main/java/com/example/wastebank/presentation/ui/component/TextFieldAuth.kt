package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun TextFieldAuth(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = Typography.bodyMedium.copy(color = Color.Black),
        placeholder = {
            Text(
                text = placeholder,
                style = Typography.bodyMedium.copy(color = GreyMedium)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp),
        shape = RoundedCornerShape(5.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BrownMain,
            unfocusedBorderColor = BrownMain
        )

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTextFieldAuth() {
    TextFieldAuth(value = "", onValueChange = {}, placeholder = "Masukkan alamat email")
}
