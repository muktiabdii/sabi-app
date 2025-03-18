package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wastebank.domain.model.DonationDomain
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreenBg
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.ui.theme.WhiteBg

// Papua dengan Kita
@Composable
fun CardDonationDetail(
    donation: DonationDomain?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(138.dp)
            .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(20.dp))
            .background(color = GreenBg, shape = RoundedCornerShape(20.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = donation?.title.orEmpty(),
                style = Typography.headlineMedium.copy(fontSize = 18.sp),
                color = Color.Black
            )
            Text(
                text = donation?.description.orEmpty(),
                style = Typography.bodyMedium,
                color = Color.Black
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
                    .background(color = WhiteBg, shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${donation?.totalAmount.orEmpty()} terkumpul",
                    style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCardDonationDetail() {
//    CardDonationDetail(
//        title = "Papua Dengan Kita",
//        description = "Beri Subsidi untuk mereka yang berada di Timur!",
//        percent = 67
//    )
//}