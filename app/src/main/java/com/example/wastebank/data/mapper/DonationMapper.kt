package com.example.wastebank.data.mapper

import com.example.wastebank.data.model.DonationData
import com.example.wastebank.domain.model.DonationDomain

object DonationMapper {
    fun mapToDomain(donationData: DonationData): DonationDomain {
        return DonationDomain(
            title = donationData.title,
            description = donationData.description,
            image = donationData.image,
            location = donationData.location,
            accountName = donationData.accountName,
            accountNumber = donationData.accountNumber,
            bank = donationData.bank,
            collected = donationData.totalAmount
        )
    }
}
