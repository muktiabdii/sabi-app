package com.example.wastebank.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.domain.usecase.UploadcareUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UploadcareViewModel(private val uploadcareUseCase: UploadcareUseCase) : ViewModel() {
    private val _uploadResult = MutableStateFlow<String?>(null)
    val uploadResult: StateFlow<String?> = _uploadResult

    fun resetUploadResult() {
        _uploadResult.value = null
    }

    // Fungsi untuk mengunggah gambar
    fun uploadImage(fileUri: Uri) {
        viewModelScope.launch {
            try {
                val uploadedUrl = uploadcareUseCase.uploadImage(fileUri).toString()
                _uploadResult.value = uploadedUrl

            } catch (e: Exception) {
                _uploadResult.value = null
            }
        }
    }


    class Factory(private val uploadcareUseCase: UploadcareUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UploadcareViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UploadcareViewModel(uploadcareUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}