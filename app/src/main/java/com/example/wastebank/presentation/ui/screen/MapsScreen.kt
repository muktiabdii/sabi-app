package com.example.wastebank.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.ButtonDay
import com.example.wastebank.presentation.ui.component.SwitchSchedule

@Composable
fun MapsScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.maps_bg),
            contentDescription = "Maps Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, bottom = 90.dp, start = 24.dp, end = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // SwitchSchedule di bagian atas
            SwitchSchedule(navController = navController)
            Spacer(modifier = Modifier.weight(1f))

            // ButtonDay di bagian bawah
            ButtonDay()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMapsScreen() {
    MapsScreen(navController = rememberNavController())
}
