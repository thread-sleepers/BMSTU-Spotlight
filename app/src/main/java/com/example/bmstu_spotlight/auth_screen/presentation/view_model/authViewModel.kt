package com.example.bmstu_spotlight.auth_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.auth_screen.data.repository.AuthRepository
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

enum class AuthState{
     LOADING, AUTHENTICATED, UNAUTHENTICATED
}

class AuthViewModel(
    private val appPreferences: AppPreferencesManager,
    private val repository: AuthRepository
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.LOADING)
    val authState: StateFlow<AuthState> = _authState

    init {
        checkAuthState()
    }

    fun checkAuthState() {
        viewModelScope.launch {
            appPreferences.authToken.collect { token ->
                if (!token.isNullOrEmpty()) {
                   _authState.value = AuthState.AUTHENTICATED
                } else {
                    _authState.value =AuthState.UNAUTHENTICATED
                }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.LOADING
            repository.login(email, password)
                .fold(
                    onSuccess = {
                        _authState.value = AuthState.AUTHENTICATED
                    },
                    onFailure = {
                        _authState.value = AuthState.UNAUTHENTICATED
                    }
                )
        }
    }
}
