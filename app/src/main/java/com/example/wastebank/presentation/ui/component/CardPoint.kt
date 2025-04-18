package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.YellowMain

@Composable
fun CardPoint(
    points: Int,
    onViewPointsClick: () -> Unit,
    onRedeemPointsClick: () -> Unit
) {
    val cardShape = RoundedCornerShape(20.dp)

    Column(
        modifier = Modifier
            .border(1.dp, BrownMain, cardShape)
            .width(370.dp)
            .height(190.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(GreenBg, cardShape)
            .clickable { onViewPointsClick() },
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = points.toString(),
                    style = Typography.headlineSmall.copy(fontSize = 50.sp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "poin",
                    style = Typography.headlineSmall.copy(fontSize = 20.sp),
                    modifier = Modifier.offset(y = (-8).dp)
                )
            }

            Text(
                text = "poin kamu saat ini",
                style = Typography.bodyLarge.copy(color = GreyMedium)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.clickable { onViewPointsClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lihat poin",
                    style = Typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "Next",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // tukar poin
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .clickable { onRedeemPointsClick() }
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tukarkan Poin Kamu!",
                    style = Typography.headlineMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "Next",
                    tint = Color(0xFF854836),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewCardPoint() {
    CardPoint(
        points = 2540,
        onViewPointsClick = { },
        onRedeemPointsClick = { }
    )
}
