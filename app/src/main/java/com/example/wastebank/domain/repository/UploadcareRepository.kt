package com.example.wastebank.domain.repository

import android.net.Uri
import java.net.URI

interface UploadcareRepository {
    suspend fun uploadImage(fileUri: Uri): URI
}
