package com.example.wastebank.domain.repository

import com.example.wastebank.domain.model.PickupDomain

interface PickupRepository {
    suspend fun requestPickup(pickupDomain: PickupDomain): Result<Boolean>
}