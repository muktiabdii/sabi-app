package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun TextFieldNominal(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "0"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(1.dp, BrownMain, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // prefix rp
        Box(
            modifier = Modifier
                .background(BrownMain, RoundedCornerShape(12.dp))
                .fillMaxHeight()
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Rp",
                style = Typography.headlineSmall.copy(color = Color.White)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))

        // input nominal
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                textStyle = Typography.bodyLarge.copy(color = Color.Black),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number).copy(
                    // nonaktifkan enter
                    imeAction = ImeAction.Done
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = Typography.bodyLarge.copy(color = GreyMedium)
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextFieldNominal() {
    var nominal by remember { mutableStateOf("") }

    TextFieldNominal(
        value = nominal,
        onValueChange = { nominal = it }
    )
}
