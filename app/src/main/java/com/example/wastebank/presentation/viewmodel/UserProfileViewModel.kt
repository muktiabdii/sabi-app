package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wastebank.domain.usecase.UserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserProfileViewModel(private val userProfileUseCase: UserProfileUseCase) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> = _gender

    private val _point = MutableStateFlow(0)
    val userPoint: StateFlow<Int> = _point

    fun getUserProfile() {
        userProfileUseCase.getUserProfile { name, email, phoneNumber, gender, point ->
            _name.value = name ?: ""
            _email.value = email ?: ""
            _phoneNumber.value = phoneNumber ?: ""
            _gender.value = gender ?: ""
            _point.value = point ?: 0
        }
    }

    fun getUserPoint() {
        userProfileUseCase.getUserPoint { point ->
            _point.value = point ?: 0
        }
    }

    fun getUserName() {
        userProfileUseCase.getUserName { name ->
            _name.value = name ?: ""
        }
    }


    class Factory(private val userProfileUseCase: UserProfileUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
                return UserProfileViewModel(userProfileUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
