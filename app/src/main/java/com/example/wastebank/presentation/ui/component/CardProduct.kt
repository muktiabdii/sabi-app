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
import coil.compose.AsyncImage
import com.example.wastebank.R
import com.example.wastebank.domain.model.ProductCategory
import com.example.wastebank.domain.model.ProductDomain
import com.example.wastebank.presentation.ui.theme.*

@Composable
fun CardProduct(
    product: ProductDomain,
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
            AsyncImage(
                model = product.image,
                contentDescription = "gambar produk",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 5.dp),
            verticalArrangement = Arrangement.Center
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
                    // kategori produk
                    Text(
                        text = product.category,
                        style = Typography.bodyMedium.copy(
                            color = GreyMedium,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = GreyMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    // harga produk
                    Text(
                        text = product.formatRupiah(),
                        style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // ikon tas untuk menambah ke keranjang
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

//@Preview(showBackground = false)
//@Composable
//fun PreviewCardProduct() {
//    CardProduct(
//        productImageResId = R.drawable.product_pot,
//        productName = "Pot Bunga Hewan",
//        productCategory = ProductCategory.VASE.toString(),
//        productPrice = "Rp20.000",
//        modifier = Modifier
//            .width(135.dp)
//            .height(175.dp),
//        imageHeight = 100,
//        onClick = { },
//        onAddToCart = { }
//    )
//}
