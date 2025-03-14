package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun TextFieldAuth(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp)
            .border(1.dp, BrownMain, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
    ) {
        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            textStyle = Typography.bodyLarge.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxSize(),
            keyboardOptions = keyboardOptions.copy(
                imeAction = ImeAction.Done
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = Typography.bodyLarge.copy(color = GreyMedium),
                    )
                }
                innerTextField()
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTextFieldAuth() {
//    TextFieldAuth(value = "", onValueChange = {}, placeholder = "Masukkan alamat email")
//}
