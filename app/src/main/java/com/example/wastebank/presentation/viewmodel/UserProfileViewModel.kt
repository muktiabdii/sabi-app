package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.domain.model.UserDomain
import com.example.wastebank.domain.usecase.UserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel(private val userProfileUseCase: UserProfileUseCase) : ViewModel() {

    private val _userProfile = MutableStateFlow<UserDomain?>(null)
    val userProfile: StateFlow<UserDomain?> = _userProfile

    private val _updateSuccess = MutableStateFlow<Boolean?>(null)
    val updateSuccess: StateFlow<Boolean?> = _updateSuccess

    private val _deleteSuccess = MutableStateFlow<Boolean?>(null)
    val deleteSuccess: StateFlow<Boolean?> = _deleteSuccess

    fun getUserProfile() {
        viewModelScope.launch {
            val profile = userProfileUseCase.getUserProfile()
            _userProfile.value = profile
        }
    }

    fun editUserProfile(user: UserDomain) {
        viewModelScope.launch {
            val result = userProfileUseCase.editUserProfile(user)
            _updateSuccess.value = result.isSuccess
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            val result = userProfileUseCase.deleteAccount()
            _deleteSuccess.value = result.isSuccess
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