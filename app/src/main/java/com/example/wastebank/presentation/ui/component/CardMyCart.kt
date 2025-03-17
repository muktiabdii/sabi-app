package com.example.wastebank.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.R
import com.example.wastebank.domain.model.Product

@Composable
fun CardMyCart(
    product: Product,
    quantity: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // gambar produk
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            modifier = Modifier.size(70.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

        // informasi Produk
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // nama produk
                Text(
                    text = product.name,
                    style = Typography.headlineMedium,
                    modifier = Modifier.weight(1f)
                )

                // jumlah produk
                Text(
                    text = quantity.toString(),
                    style = Typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(4.dp))

                // icon naik turun
                Image(
                    painter = painterResource(id = R.drawable.ic_count),
                    contentDescription = "Quantity Selector",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                // harga produk
                Text(
                    text = "product.formattedPrice",
                    style = Typography.headlineSmall
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // kategori produk
            Text(
                text = product.category.displayName,
                style = Typography.bodyMedium
            )
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun PreviewCardMyCart() {
//    CardMyCart(
//        product = ProductDataSource.productList[2],
//        quantity = 1
//    )
//}
