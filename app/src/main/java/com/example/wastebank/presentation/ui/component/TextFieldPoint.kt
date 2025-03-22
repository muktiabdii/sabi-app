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
fun TextFieldPoint(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "0"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(1.dp, BrownMain, RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(12.dp))

        // Input point
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                textStyle = Typography.bodyLarge.copy(color = Color.Black),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number).copy(
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

        // Suffix pts
        Box(
            modifier = Modifier
                .background(
                    color = BrownMain,
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                )
                .fillMaxHeight()
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "pts",
                style = Typography.headlineSmall.copy(color = Color.White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextFieldPoint() {
    var points by remember { mutableStateOf("") }

    TextFieldPoint(
        value = points,
        onValueChange = { points = it }
    )
}