package com.example.wastebank.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.domain.model.DonateDomain
import com.example.wastebank.domain.model.DonationDomain
import com.example.wastebank.domain.usecase.DonationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DonationViewModel(private val donationUseCase: DonationUseCase) : ViewModel() {

    private val _donations = MutableStateFlow<List<DonationDomain>>(emptyList())
    val donations: StateFlow<List<DonationDomain>> = _donations

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _selectedDonation = MutableStateFlow<DonationDomain?>(null)
    val selectedDonation: StateFlow<DonationDomain?> = _selectedDonation.asStateFlow()

    private val _donateState = MutableStateFlow<Result<Boolean>?>(null)
    val donateState: StateFlow<Result<Boolean>?> = _donateState.asStateFlow()

    private val _proofImageUrl = MutableStateFlow<String?>(null)
    val proofImageUrl: StateFlow<String?> = _proofImageUrl

    private val totalAmount = MutableStateFlow<Int?>(null)
    val totalAmountState: StateFlow<Int?> = totalAmount.asStateFlow()

    fun setProofImageUrl(url: String) {
        _proofImageUrl.value = url
    }

    fun updateTotalAmount(selectedNominal: Int?, customNominal: String) {
        val nominal = selectedNominal ?: customNominal.toIntOrNull() ?: 0
        totalAmount.value = nominal
    }

    fun getAllDonations() {
        viewModelScope.launch {
            donationUseCase.getAllDonations()
                .catch { e ->
                    _errorMessage.value = e.localizedMessage ?: "Terjadi kesalahan"
                }
                .collectLatest { result ->
                    _donations.value = result
                }
        }
    }

    fun getDonationByTitle(title: String) {
        viewModelScope.launch {
            val result = donationUseCase.getDonationByTitle(title)
            _selectedDonation.value = result

        }
    }

    fun donate(selectedOption: String) {
        viewModelScope.launch {
            _donateState.value = Result.success(false) // Menandakan proses sedang berjalan

            val proofUrl = _proofImageUrl.value
            val donation = _selectedDonation.value ?: DonationDomain()

            val donateData = DonateDomain(
                donateMethod = if (selectedOption == "Transfer Bank") "money" else "points",
                donations = donation,
                totalAmount = totalAmount.value,
                receiptImage = proofUrl ?: "",
            )
            Log.d("DonationViewModel", "Total Amount: ${donation.collected}")


            val result = donationUseCase.donate(donateData)
            _donateState.value = result

            result.onSuccess {
                _errorMessage.value = null
            }.onFailure { exception ->
                _errorMessage.value = exception.message
            }
        }
    }

    class Factory(private val donationUseCase: DonationUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DonationViewModel::class.java)) {
                return DonationViewModel(donationUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
