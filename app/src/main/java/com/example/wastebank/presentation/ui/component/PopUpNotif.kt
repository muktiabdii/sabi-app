package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.BrownBg
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun PopUpNotif(
    iconResId: Int,
    message: String,
    buttonText: String,
    navController: NavController? = null,
    destination: String? = null,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { }) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(BrownBg)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Popup Icon",
                    tint = BrownMain,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = message,
                    style = Typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onDismiss()
                        if (navController != null && destination != null) {
                            navController.navigate(destination)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BrownMain),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(180.dp)
                        .height(40.dp)
                ) {
                    Text(
                        text = buttonText,
                        style = Typography.headlineMedium,
                        color = BrownBg
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPopUpNotif() {
    PopUpNotif(
        iconResId = R.drawable.ic_success,
        message = "Permintaan terkirim!",
        buttonText = "KEMBALI MASUK",
        destination = null,
        onDismiss = { }
    )
}
