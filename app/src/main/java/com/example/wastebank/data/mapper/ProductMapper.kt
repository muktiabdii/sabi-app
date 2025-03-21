package com.example.wastebank.data.mapper

import com.example.wastebank.data.model.ProductData
import com.example.wastebank.domain.model.ProductDomain

// mapper untuk mengonversi data ProductData ke ProductDomain
object ProductMapper {
    fun mapToDomain(productData: ProductData): ProductDomain {
        return ProductDomain(
            name = productData.name,
            category = productData.category,
            price = productData.price,  // Format harga agar siap digunakan di UI
            image = productData.image,
            description = productData.description
        )
    }
}
