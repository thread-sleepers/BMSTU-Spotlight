package com.example.bmstu_spotlight.profile.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import com.example.bmstu_spotlight.profile.domain.repository.ProfileRepository
import com.example.bmstu_spotlight.profile.domain.model.UserProfile
import com.example.bmstu_spotlight.profile.presentation.ui_state.UiState
import com.example.bmstu_spotlight.ui.screens.RootScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val appPreferencesManager: AppPreferencesManager
) : ViewModel() {

    private val _userProfile = MutableStateFlow<UiState<UserProfile>>(UiState.loading())
    val userProfile: StateFlow<UiState<UserProfile>> = _userProfile

    init {
        loadUserProfile()
    }

    fun loadUserProfile() {
        viewModelScope.launch {
            _userProfile.value = UiState.loading()
            try {
                val profile = repository.getUserProfile()
                _userProfile.value = UiState.success(profile)
            } catch (e: Exception) {
                _userProfile.value = UiState.error(e)
            }
        }
    }

    fun signOut(navController: NavController) {
        viewModelScope.launch {
            appPreferencesManager.clearAuthToken()
            navController.navigate(RootScreen.Auth.route) {
                popUpTo(RootScreen.MainApp.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }
}
