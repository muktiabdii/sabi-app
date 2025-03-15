package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R

@Composable
fun CardDonate(
    imageResId: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(160.dp)
            .height(250.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "donate section",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 12.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun CardDonatePreview() {
    CardDonate(
        imageResId = R.drawable.donate_rumah_portrait,
        title = "Rumah Dhuafa Ayna",
        description = "Bantu anak-anak untuk beli peralatan sekolah."
    )
}
