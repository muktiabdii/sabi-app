package com.example.wastebank.presentation.ui.screen.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.component.SearchBarMarket
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyMedium
import com.example.wastebank.presentation.ui.theme.Typography

@Composable
fun ArticleScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // Search Bar
            SearchBarMarket(
                value = "",
                onValueChange = {},
                placeholder = "Cari Berita dan Modul Belajarmu"
            )
        }

        item {
            // Konten Terpilih
            Text(text = "Konten Terpilih", style = Typography.headlineLarge)
            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = painterResource(id = R.drawable.article_main_1),
                contentDescription = "Konten Terpilih",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            // Artikel
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Artikel", style = Typography.headlineLarge)
                Text(text = "Lihat semua", style = Typography.bodyLarge, color = BrownMain)
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.article_sec_2),
                        contentDescription = "Artikel 1",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Cara Mengolah Botol Plastik",
                        style = Typography.bodyLarge
                    )
                    Text(
                        text = "Jumat, 14 Maret 2025",
                        style = Typography.bodyMedium,
                        color = GreyMedium
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.article_sec_3),
                        contentDescription = "Artikel 2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Pro Kontra Usaha Botol Plastik Warga Lowokwaru",
                        style = Typography.bodyLarge
                    )
                    Text(
                        text = "Jumat, 14 Maret 2025",
                        style = Typography.bodyMedium,
                        color = GreyMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            // Kumpulan Pembelajaran
            Text(text = "Kumpulan Pembelajaran", style = Typography.headlineLarge)
        }

        item {
            // Wrapper untuk LazyVerticalGrid
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp, max = 10000.dp)
                ) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.lesson_1),
                            contentDescription = "Lesson 1",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.lesson_2),
                            contentDescription = "Lesson 2",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.lesson_3),
                            contentDescription = "Lesson 3",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.lesson_4),
                            contentDescription = "Lesson 4",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(60.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    val navController = rememberNavController()
    ArticleScreen(navController)
}
