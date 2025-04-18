package com.example.wastebank.presentation.ui.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.animateDpAsState
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.Typography

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun SwitchSchedule(
    navController: NavController,
    modifier: Modifier = Modifier,
    defaultSelected: Boolean = false
) {
    // state untuk menentukan pilihan aktif
    var isRequestSelected by remember { mutableStateOf(defaultSelected) }

    // animasi pergeseran tombol switch
    val switchOffset by animateDpAsState(
        // geser ke kanan jika request aktif
        targetValue = if (isRequestSelected) 150.dp else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "switchOffset"
    )

    Box(
        modifier = modifier
            .width(300.dp)
            .height(45.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(1.dp, BrownMain, RoundedCornerShape(10.dp))
            .padding(3.dp)
    ) {
        // indikator latar belakang yang berpindah
        Box(
            modifier = Modifier
                .width(145.dp)
                .height(39.dp)
                .offset(x = switchOffset)
                .background(BrownMain, RoundedCornerShape(10.dp))
        )

        Row(modifier = Modifier.fillMaxSize()) {
            // tombol jadwal
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        isRequestSelected = false
                        navController.navigate("maps_screen")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Jadwal",
                    style = Typography.headlineMedium,
                    color = if (!isRequestSelected) Color.White else BrownMain,
                    textAlign = TextAlign.Center
                )
            }

            // tombol request
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        isRequestSelected = true
                        navController.navigate("request_screen")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Request",
                    style = Typography.headlineMedium,
                    color = if (isRequestSelected) Color.White else BrownMain,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SwitchSchedulePreview() {
    val navController = rememberNavController()
    SwitchSchedule(navController = navController)
}
