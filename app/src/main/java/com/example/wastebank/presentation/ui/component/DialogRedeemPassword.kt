package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.YellowMain

@Composable
fun DialogRedeemPassword(
    password: String,
    onPasswordChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Title
                Text(
                    text = "Masukkan Kata Sandi",
                    style = Typography.headlineLarge.copy(color = Color.Black)
                )
                Spacer(modifier = Modifier.height(40.dp))

                // Password Field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Kata Sandi",
                        style = Typography.bodyLarge.copy(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextFieldPassword(
                        value = password,
                        onValueChange = { onPasswordChange(it) },
                        placeholder = "Masukkan kata sandi"
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))

                // Confirm Button
                ButtonAuth(
                    text = "KONFIRMASI",
                    backgroundColor = BrownMain,
                    textColor = Color.White,
                    onClick = { onConfirm() }
                )
            }
        }
    }
}

//@Preview(showBackground = false)
//@Composable
//fun PreviewRedeemPointDialog() {
//    RedeemPointDialog(onDismiss = {}, onConfirm = {})
//}
