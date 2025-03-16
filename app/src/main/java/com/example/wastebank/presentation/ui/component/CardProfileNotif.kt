package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.*

@Composable
fun CardProfileNotif(
    iconResId: Int,
    backgroundColor: Color,
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(10.dp),
        border = ButtonDefaults.outlinedButtonBorder,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // icon dengan latar belakang warna tertentu
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .background(color = backgroundColor, shape = RoundedCornerShape(size = 8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = text,
                    tint = BrownMain
                )
            }

            // teks menu
            Text(
                text = text,
                style = Typography.bodyLarge,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )

            // toggle switch on/off
            Switch(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                // ganti warna sesuai on/off
                colors = SwitchDefaults.colors(
                    checkedTrackColor = BrownMain,
                    uncheckedTrackColor = Color.Gray,
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardProfileNotif() {
    var isNotifEnabled by remember { mutableStateOf(true) }

    CardProfileNotif(
        iconResId = R.drawable.ic_notif,
        backgroundColor = GreenBg,
        text = "Notifikasi",
        isChecked = isNotifEnabled,
        onCheckedChange = { isNotifEnabled = it }
    )
}
