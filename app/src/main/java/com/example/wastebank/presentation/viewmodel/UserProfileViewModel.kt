package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wastebank.domain.usecase.UserProfileUseCase

class UserProfileViewModel(private val userProfileUseCase: UserProfileUseCase) : ViewModel() {

    private val _userPoint = MutableLiveData<Int>()
    val userPoint: LiveData<Int> get() = _userPoint

    fun getUserPoint() {
        userProfileUseCase.getUserPoin { point ->
            _userPoint.postValue(point ?: 0)
        }
    }
}