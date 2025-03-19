package com.example.wastebank.domain.usecase

import android.net.Uri
import com.example.wastebank.domain.repository.UploadcareRepository
import java.net.URI

class UploadcareUseCase(private val uploadcareRepository: UploadcareRepository) {
    suspend fun uploadImage(fileUri: Uri): URI {
        return uploadcareRepository.uploadImage(fileUri)
    }
}