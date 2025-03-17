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
import com.example.wastebank.domain.model.ProductCategory
import com.example.wastebank.presentation.ui.theme.*

@Composable
fun CardProduct(
    productImageResId: Int,
    productName: String,
    productCategory: String,
    productPrice: String,
    modifier: Modifier = Modifier,
    imageHeight: Int = 100,
    onClick: () -> Unit, // navigasi ke detail produk
    onAddToCart: () -> Unit // menambahkan ke keranjang
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(WhiteBg)
            .clickable { onClick() },
    ) {
        // gambar produk
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight.dp),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = productImageResId),
                contentDescription = "gambar produk",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            // nama produk
            Text(
                text = productName,
                style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))

            // row untuk kategori, harga, dan ikon
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // kategori produk
                    Text(
                        text = productCategory,
                        style = Typography.bodySmall,
                        color = GreyMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    // harga produk
                    Text(
                        text = productPrice,
                        style = Typography.bodySmall,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // ikon tas untuk menambah ke keranjang
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(50))
                        .background(YellowMain)
                        .clickable { onAddToCart() }, // event saat tombol diklik
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.clip(RoundedCornerShape(50)),
                        painter = painterResource(id = R.drawable.ic_bag),
                        contentDescription = "tambah ke keranjang",
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
        productImageResId = R.drawable.product_pot,
        productName = "Pot Bunga Hewan",
        productCategory = ProductCategory.VASE.toString(),
        productPrice = "Rp20.000",
        modifier = Modifier
            .width(135.dp)
            .height(175.dp),
        imageHeight = 100,
        onClick = { },
        onAddToCart = { }
    )
}
