package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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

    class Factory(private val donationUseCase: DonationUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DonationViewModel::class.java)) {
                return DonationViewModel(donationUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
