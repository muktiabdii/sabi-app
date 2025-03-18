package com.example.wastebank.domain.repository

import com.example.wastebank.data.model.DonationData
import com.example.wastebank.domain.model.DonationDomain
import kotlinx.coroutines.flow.Flow

interface DonationRepository {
    fun getAllDonation(): Flow<List<DonationDomain>>
    suspend fun getDonationByTitle(title: String): DonationDomain?
}
