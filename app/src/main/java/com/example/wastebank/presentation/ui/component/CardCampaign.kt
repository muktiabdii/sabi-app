package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyLight
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.YellowBg

@Composable
fun CardCampaign(
    monthName: String,
    daysLeft: Int,
    completedMissions: Int,
    totalMissions: Int
) {
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(90.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // kampanye bulan maret
            Text(
                text = "Kampanye Bulan $monthName",
                style = Typography.headlineMedium
            )
            // 25 hari lagi
            Text(
                text = "$daysLeft hari lagi",
                style = Typography.bodyLarge
            )

        }
        Spacer(modifier = Modifier.height(10.dp))

        // progress bar
        val progressFraction = completedMissions.toFloat() / totalMissions.toFloat()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(GreyLight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progressFraction)
                    .height(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BrownMain)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // 3 / 30 misi
        Text(
            text = "$completedMissions / $totalMissions Misi Terselesaikan",
            style = Typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CampaignScreen() {
    CardCampaign(
        monthName = "Maret",
        daysLeft = 25,
        completedMissions = 3,
        totalMissions = 30
    )
}