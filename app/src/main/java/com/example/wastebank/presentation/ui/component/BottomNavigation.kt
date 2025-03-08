package com.example.wastebank.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.presentation.ui.theme.YellowMain
import kotlinx.coroutines.delay

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Maps,
        BottomNavItem.Market,
        BottomNavItem.Article,
        BottomNavItem.Profile
    )

    val currentRoute = remember { mutableStateOf("home") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .border(
                width = 1.dp,
                color = YellowMain,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .padding(1.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            ),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        color = Color.White,
        shadowElevation = 10.dp

    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                // animasi ketika menu dipilih
                val isSelected = currentRoute.value == item.route
                var alpha by remember { mutableStateOf(0f) }
                val animatedAlpha by animateFloatAsState(
                    targetValue = alpha,
                    animationSpec = tween(300), label = ""
                )

                LaunchedEffect(alpha) {
                    if (alpha > 0) {
                        delay(200)
                        alpha = 0f
                    }
                }

                Box(
                    modifier = Modifier
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // efek abu-abu dengan animasi
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.LightGray.copy(alpha = animatedAlpha), CircleShape)
                    )

                    IconButton(
                        onClick = {
                            if (!isSelected) {
                                alpha = 0.3f // munculkan efek abu-abu
                                currentRoute.value = item.route
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(id = if (isSelected) item.icon else item.iconOff),
                            contentDescription = item.title,
                            tint = YellowMain
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()
    BottomNavigation(navController = navController)
}