package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun TopBar(username: String, points: Int = 0, role: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo Sabi
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Sabi",
            modifier = Modifier
                .width(104.dp)
                .height(46.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Teks username dan poin rata kanan
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Hi, $username",
                style = Typography.headlineMedium
            )
            if (role == "user") {
                Text(
                    text = "Poin saat ini: $points pts",
                    style = Typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    TopBar(username = "Raion", points = 2450, role = "user")
}
