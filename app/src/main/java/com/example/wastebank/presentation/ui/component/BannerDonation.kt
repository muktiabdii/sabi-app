package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wastebank.R

@Composable
fun BannerDonation(navController: NavController) {
    val images = listOf(
        R.drawable.donate_papua_landscape,
        R.drawable.donate_palestina_landscape,
        R.drawable.donate_mengajar_landscape,
        R.drawable.donate_jalanan_landscape
    )

    val pagerState = rememberPagerState { images.size }

    // pager untuk slideshow
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
    ) { page ->
        Image(
            painter = painterResource(id = images[page]),
            contentDescription = "Banner Donasi",
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate("donate_screen")
                }
        )
    }
}