package com.example.wastebank.presentation.ui.screen.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.GreyLine
import com.example.wastebank.presentation.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> OnboardingPage(
                    imageRes = R.drawable.logo,
                    imageSize = 240,
                    title = "Selamat datang di SABI.",
                    description = "Ayo kelola sampah di Kota Malang yang masih belum terkelola bersama kami setiap harinya!"
                )

                1 -> OnboardingPage(
                    imageRes = R.drawable.onboard_shop,
                    imageSize = 350,
                    title = "Belanja Bersama SABI!",
                    description = "E-commerce kami akan membantu menemukan barang olahan yang tepat untuk kebutuhan diri maupun rumahmu!"
                )

                2 -> OnboardingPage(
                    imageRes = R.drawable.onboard_truck,
                    imageSize = 330,
                    title = "Terjadwal Bersama SABI!",
                    description = "Bergabung dengan tetangga, menjadwal penjemput sampah, dan raih rewards bersama!"
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.2f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Skip",
                style = Typography.headlineLarge.copy(fontWeight = FontWeight.SemiBold),
                color = GreyLine,
                modifier = Modifier.clickable {
                    navController.navigate("login_role_screen")
                }
            )

            PageIndicator(pagerState.currentPage)

            Text(
                text = "Next",
                style = Typography.headlineLarge,
                color = BrownMain,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        if (pagerState.currentPage < 2) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            navController.navigate("login_role_screen")
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun OnboardingPage(imageRes: Int, imageSize: Int, title: String, description: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
    ) {
        Box(
            modifier = Modifier.size(320.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(imageSize.dp)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = title,
                    style = Typography.headlineLarge.copy(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = description,
                    style = Typography.headlineMedium.copy(fontWeight = FontWeight.Medium),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun PageIndicator(currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(3) { index ->
            val alpha by animateFloatAsState(if (index == currentPage) 1f else 0.3f)

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (index == currentPage) BrownMain else BrownMain.copy(alpha = alpha))
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(navController = rememberNavController())
}

