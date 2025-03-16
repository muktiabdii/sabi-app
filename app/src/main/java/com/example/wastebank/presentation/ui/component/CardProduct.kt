package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.R
import com.example.wastebank.domain.model.Product
import com.example.wastebank.domain.model.ProductCategory
import com.example.wastebank.presentation.ui.theme.*

@Composable
fun CardProduct(
    product: Product,
    modifier: Modifier = Modifier,
    imageHeight: Int = 100,
    onClick: () -> Unit,
    onAddToCart: () -> Unit
) {
    Column(
        modifier = modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(WhiteBg)
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight.dp),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = "Gambar produk",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = product.name,
                style = Typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = product.category.displayName,
                        style = Typography.bodyMedium.copy(
                            color = GreyMedium,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = GreyMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = product.formattedPrice,
                        style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(RoundedCornerShape(50))
                        .background(YellowMain)
                        .clickable { onAddToCart() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(50)),
                        painter = painterResource(id = R.drawable.ic_bag),
                        contentDescription = "Tambah ke keranjang",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewCardProduct() {
    CardProduct(
        product = Product(
            name = "Pot Bunga Hewan",
            category = ProductCategory.VASE,
            price = 20000,
            imageRes = R.drawable.product_pot,
            description = "Pot bunga lucu berbentuk hewan yang dibuat dari botol plastik bekas. Desainnya menarik dengan berbagai karakter seperti kucing, panda, dan kelinci."
        ),
        modifier = Modifier
            .width(160.dp)
            .height(195.dp),
        imageHeight = 110,
        onClick = { },
        onAddToCart = { }
    )
}
