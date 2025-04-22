package com.example.bmstu_spotlight.profile.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.profile.domain.repository.ProfileRepository
import com.example.bmstu_spotlight.profile.domain.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    init {
        viewModelScope.launch {
            _userProfile.value = repository.getUserProfile()
        }
    }

    fun signOut() {
        // TODO: Реализация выхода
    }
}
