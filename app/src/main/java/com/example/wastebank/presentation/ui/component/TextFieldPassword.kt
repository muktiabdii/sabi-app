package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun TextFieldPassword(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

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
            textStyle = Typography.bodyLarge.copy(
                color = Color.Black,
                textDecoration = TextDecoration.None
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                // nonaktifkan enter
                imeAction = ImeAction.Done
            ),

            // sembunyikan isi password
            visualTransformation =
            if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation('•'),

            modifier = Modifier.fillMaxSize(),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            androidx.compose.material3.Text(
                                text = placeholder,
                                style = Typography.bodyLarge.copy(
                                    color = GreyMedium,
                                    textDecoration = TextDecoration.None
                                ),
                            )
                        }
                        innerTextField()
                    }
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        // atur image untuk hide dan show
                        Image(
                            painter = painterResource(if (isPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed),
                            contentDescription = if (isPasswordVisible) "Sembunyikan password" else "Tampilkan password"
                        )
                    }
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTextFieldPassword() {
//    TextFieldPassword(value = "", onValueChange = {}, placeholder = "Minimal 8 karakter")
//}
