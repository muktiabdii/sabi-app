package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyLight
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.YellowBg
import com.example.wastebank.presentation.ui.theme.YellowMain

@Composable
fun CardMission(
    missionTitle: String,
    missionDescription: String,
    ptsReward: Int,
    currentProgress: Int,
    totalProgress: Int,
    progressSuffix: String
) {
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(12.dp))
            .width(370.dp)
            .height(100.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // icon jam
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "clock icon",
                    modifier = Modifier
                        .size(15.dp),
                    tint = YellowMain
                )
                Spacer(modifier = Modifier.width(6.dp))

                // misi harian
                Text(
                    text = missionTitle,
                    style = Typography.headlineSmall
                )
            }

            // 100 pts
            Text(
                text = "+$ptsReward pts",
                style = Typography.headlineSmall,
                color = BrownMain
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        // kumpulin botol
        Text(
            text = missionDescription,
            style = Typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // progress bar
        val progressFraction = currentProgress.toFloat() / totalProgress.toFloat()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(GreyLight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progressFraction)
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(YellowMain)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        // 8 dari 10
        Text(
            text = "$currentProgress dari $totalProgress $progressSuffix",
            style = Typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardMission() {
    CardMission(
        missionTitle = "Misi Harian",
        missionDescription = "Kumpulin 10 botol plastik",
        ptsReward = 100,
        currentProgress = 8,
        totalProgress = 10,
        progressSuffix = "botol sudah terkumpul!"
    )
}