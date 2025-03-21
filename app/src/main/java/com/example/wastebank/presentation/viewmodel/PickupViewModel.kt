package com.example.wastebank.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.domain.model.PickupDomain
import com.example.wastebank.domain.usecase.PickupUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PickupViewModel(private val pickupUseCase: PickupUseCase) : ViewModel() {

    private val _pickupData = MutableStateFlow(PickupDomain())
    val pickupData: StateFlow<PickupDomain> = _pickupData.asStateFlow()

    private val _isRequestSuccessful = MutableStateFlow<Boolean?>(null)
    val isRequestSuccessful: StateFlow<Boolean?> = _isRequestSuccessful.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _proofImageUrl = MutableStateFlow<String?>(null)
    val proofImageUrl: StateFlow<String?> = _proofImageUrl

    fun setProofImageUrl(url: String) {
        _proofImageUrl.value = url
    }

    fun resetProofImageUrl() {
        _proofImageUrl.value = null
    }

    fun resetPickupData() {
        _pickupData.value = PickupDomain()
    }



    fun updatePickupData(updatedData: PickupDomain) {
        _pickupData.value = updatedData
    }

    fun requestPickup(pickupRequest: PickupDomain) {
        viewModelScope.launch {
            val proofUrl = _proofImageUrl.value
            Log.d("proofUrl", proofUrl.toString())

            val pickupData = pickupRequest.copy(
                image = proofUrl ?: "" // Tambahkan gambar ke data pickup
            )

            Log.d("pickupData", pickupData.toString())

            val result = pickupUseCase.requestPickup(pickupData)
            result.fold(
                onSuccess = {
                    _pickupData.value = pickupData
                    _isRequestSuccessful.value = true
                },
                onFailure = { error ->
                    _errorMessage.value = error.message
                }
            )
        }
    }

    class Factory(private val pickupUseCase: PickupUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PickupViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PickupViewModel(pickupUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
