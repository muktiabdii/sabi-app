package com.example.wastebank.data

import com.example.wastebank.R
import com.example.wastebank.domain.model.Product
import com.example.wastebank.domain.model.ProductCategory

object ProductDataSource {
    val productList = listOf(
        Product(
            name = "Tas Totebag Jeans",
            category = ProductCategory.FASHION,
            price = 15000,
            imageRes = R.drawable.product_totebag_full,
            description = "Tas totebag ini terbuat dari bahan denim jeans berkualitas dengan desain unik yang menyerupai celana jeans. Dilengkapi dengan kantong-kantong kecil yang menambah nilai estetika dan fungsionalitas. Cocok digunakan untuk gaya kasual sehari-hari, membawa buku, laptop, atau barang lainnya."
        ),
        Product(
            name = "Pot Bunga Hewan",
            category = ProductCategory.VASE,
            price = 20000,
            imageRes = R.drawable.product_pot,
            description = "Pot bunga lucu berbentuk hewan yang dibuat dari botol plastik bekas. Desainnya menarik dengan berbagai karakter seperti kucing, panda, dan kelinci. Pot ini cocok untuk tanaman hias kecil seperti sukulen atau kaktus, serta menjadi dekorasi yang ramah lingkungan untuk meja atau rak rumah Anda."
        ),
        Product(
            name = "Lampu Sendok",
            category = ProductCategory.DECOR,
            price = 30000,
            imageRes = R.drawable.product_lampu_full,
            description = "Lampu gantung unik yang dibuat dari sendok plastik bekas, disusun menyerupai sisik ikan atau buah nanas. Saat dinyalakan, lampu ini memberikan efek cahaya yang indah dan artistik. Cocok untuk menghiasi kamar tidur, ruang tamu, atau kafe dengan konsep daur ulang dan estetika minimalis."
        ),
        Product(
            name = "Tempat Pensil Kayu",
            category = ProductCategory.CRAFT,
            price = 30000,
            imageRes = R.drawable.product_pensil,
            description = "Tempat pensil berbahan dasar stik es krim yang disusun dan dihiasi dengan motif bunga yang ceria. Dengan desain yang menarik dan ramah lingkungan, tempat pensil ini cocok untuk anak-anak maupun orang dewasa yang ingin menata alat tulis dengan cara yang unik dan kreatif."
        ),
        Product(
            name = "Mainan Mobil",
            category = ProductCategory.TOY,
            price = 25000,
            imageRes = R.drawable.product_mobil,
            description = "Mainan mobil daur ulang yang dibuat dari kaleng bekas dan tutup botol sebagai roda. Desainnya kreatif dan ramah lingkungan, cocok untuk anak-anak sebagai mainan edukatif atau sebagai koleksi bagi pecinta barang-barang unik dari bahan daur ulang."
        ),
        Product(
            name = "Tas Totebag Jeans",
            category = ProductCategory.FASHION,
            price = 15000,
            imageRes = R.drawable.product_totebag_full,
            description = "Tas totebag ini terbuat dari bahan denim jeans berkualitas dengan desain unik yang menyerupai celana jeans. Dilengkapi dengan kantong-kantong kecil yang menambah nilai estetika dan fungsionalitas. Cocok digunakan untuk gaya kasual sehari-hari, membawa buku, laptop, atau barang lainnya."
        ),
        Product(
            name = "Pot Bunga Hewan",
            category = ProductCategory.VASE,
            price = 20000,
            imageRes = R.drawable.product_pot,
            description = "Pot bunga lucu berbentuk hewan yang dibuat dari botol plastik bekas. Desainnya menarik dengan berbagai karakter seperti kucing, panda, dan kelinci. Pot ini cocok untuk tanaman hias kecil seperti sukulen atau kaktus, serta menjadi dekorasi yang ramah lingkungan untuk meja atau rak rumah Anda."
        ),
        Product(
            name = "Lampu Sendok",
            category = ProductCategory.DECOR,
            price = 30000,
            imageRes = R.drawable.product_lampu_full,
            description = "Lampu gantung unik yang dibuat dari sendok plastik bekas, disusun menyerupai sisik ikan atau buah nanas. Saat dinyalakan, lampu ini memberikan efek cahaya yang indah dan artistik. Cocok untuk menghiasi kamar tidur, ruang tamu, atau kafe dengan konsep daur ulang dan estetika minimalis."
        ),
        Product(
            name = "Tempat Pensil Kayu",
            category = ProductCategory.CRAFT,
            price = 30000,
            imageRes = R.drawable.product_pensil,
            description = "Tempat pensil berbahan dasar stik es krim yang disusun dan dihiasi dengan motif bunga yang ceria. Dengan desain yang menarik dan ramah lingkungan, tempat pensil ini cocok untuk anak-anak maupun orang dewasa yang ingin menata alat tulis dengan cara yang unik dan kreatif."
        ),
        Product(
            name = "Mainan Mobil",
            category = ProductCategory.TOY,
            price = 25000,
            imageRes = R.drawable.product_mobil,
            description = "Mainan mobil daur ulang yang dibuat dari kaleng bekas dan tutup botol sebagai roda. Desainnya kreatif dan ramah lingkungan, cocok untuk anak-anak sebagai mainan edukatif atau sebagai koleksi bagi pecinta barang-barang unik dari bahan daur ulang."
        )

    )
}
