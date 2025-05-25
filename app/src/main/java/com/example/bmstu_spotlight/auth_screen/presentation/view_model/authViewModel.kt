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

    private var authCheckJob: Job? = null

    init {
        checkAuthState()
    }

    fun checkAuthState() {
        authCheckJob?.cancel()
        authCheckJob = viewModelScope.launch {
            appPreferences.authToken.map { token ->
                if (!token.isNullOrEmpty()) {
                    AuthState.AUTHENTICATED
                } else {
                    AuthState.UNAUTHENTICATED
                }
            }.distinctUntilChanged()
                .collect { state ->
                    _authState.value = state
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = AuthState.LOADING
                repository.login(email, password)
                if (repository.badRequest) {
                    _authState.value = AuthState.UNAUTHENTICATED
                }
            } catch(e: Exception) {
                _authState.value = AuthState.UNAUTHENTICATED
            }
        }
    }
}
