package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.model.PickupDomain
import com.example.wastebank.domain.repository.PickupRepository

class PickupUseCase(private val pickupRepository: PickupRepository) {
    suspend fun requestPickup(pickupDomain: PickupDomain): Result<Boolean> {
        return pickupRepository.requestPickup(pickupDomain)
    }
}