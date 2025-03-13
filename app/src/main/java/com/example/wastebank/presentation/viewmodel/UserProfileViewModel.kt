package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wastebank.domain.usecase.UserProfileUseCase

class UserProfileViewModel(private val userProfileUseCase: UserProfileUseCase) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> get() = _phoneNumber

    private val _gender = MutableLiveData<String>()
    val gender: LiveData<String> get() = _gender

    private val _point = MutableLiveData<Int>()
    val userPoint: LiveData<Int> get() = _point

    fun getUserPoint() {
        userProfileUseCase.getUserPoint { point ->
            _point.postValue(point ?: 0)
        }
    }

    fun getUserProfile() {
        userProfileUseCase.getUserProfile { name, email, phoneNumber, gender, point ->
            _name.postValue(name ?: "")
            _email.postValue(email ?: "")
            _phoneNumber.postValue(phoneNumber ?: "")
            _gender.postValue(gender ?: "")
            _point.postValue(point ?: 0)
        }
    }
}
