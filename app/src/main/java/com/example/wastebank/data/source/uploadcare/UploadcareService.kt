package com.example.wastebank.data.source.uploadcare

import com.uploadcare.android.library.api.UploadcareClient

object UploadcareService {
    val client: UploadcareClient by lazy {
        UploadcareClient("8a6ca98abf24a3f4cfb0")
    }
}