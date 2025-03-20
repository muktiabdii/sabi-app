package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.model.DonationDomain
import com.example.wastebank.domain.repository.DonationRepository
import kotlinx.coroutines.flow.Flow

class DonationUseCase(private val donationRepository: DonationRepository) {
    fun getAllDonations(): Flow<List<DonationDomain>> {
        return donationRepository.getAllDonation()
    }

    suspend fun getDonationByTitle(title: String): DonationDomain? {
        return donationRepository.getDonationByTitle(title)
    }
}
