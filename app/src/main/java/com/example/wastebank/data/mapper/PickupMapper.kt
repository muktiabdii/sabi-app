package com.example.wastebank.data.mapper

import com.example.wastebank.data.model.PickupData
import com.example.wastebank.domain.model.PickupDomain

object PickupMapper {
    fun PickupDomain.toPickupData(): PickupData {
        return PickupData(
            email = this.email,
            address = this.address,
            weight = this.weight,
            phoneNumber = this.phoneNumber,
            selectedDate = this.selectedDate,
            trashType = this.trashType,
            selectedTime = this.selectedTime,
            description = this.description,
            image = this.image
        )
    }
}