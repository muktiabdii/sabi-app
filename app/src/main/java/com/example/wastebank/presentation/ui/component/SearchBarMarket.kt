package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun SearchBarMarket(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .height(37.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Cari",
            tint = GreyMedium
        )
        Spacer(modifier = Modifier.width(10.dp))

        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            textStyle = Typography.bodyLarge.copy(color = Color.Black),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                // nonaktifkan enter
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

@Preview(showBackground = true)
@Composable
fun PreviewSearchBarMarket() {
    var text by remember { mutableStateOf("") }
    SearchBarMarket(
        value = text,
        onValueChange = { text = it },
        placeholder = "Cari Produk Olahan Kamu"
    )
}