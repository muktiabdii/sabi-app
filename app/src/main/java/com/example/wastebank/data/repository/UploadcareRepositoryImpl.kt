package com.example.wastebank.data.repository

import android.content.Context
import android.net.Uri
import com.example.wastebank.data.source.uploadcare.UploadcareService
import com.example.wastebank.domain.repository.UploadcareRepository
import com.uploadcare.android.library.exceptions.UploadcareApiException
import com.uploadcare.android.library.upload.FileUploader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URI // Import tambahan

class UploadcareRepositoryImpl(private val context: Context) : UploadcareRepository {

    private val uploadCare = UploadcareService.client

    override suspend fun uploadImage(fileUri: Uri): URI {
        return withContext(Dispatchers.IO) {
            try {
                val uploader = FileUploader(uploadCare, fileUri, context).store(true)
                val result = uploader.upload()

                val fileUrl = result.originalFileUrl ?: URI("https://ucarecdn.com/${result.uuid}/")

                return@withContext fileUrl
            } catch (e: Exception) {
                throw UploadcareApiException("Upload gagal: ${e.message ?: "Unknown error"}")
            }
        }
    }


}
