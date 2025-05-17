package com.example.bmstu_spotlight.auth_screen.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmstu_spotlight.auth_screen.domain.repository.AuthRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val _username = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginSuccess = MutableLiveData<Boolean>()
    private val loginSuccess : LiveData<Boolean> = _loginSuccess

    fun setUsername(username : String) {
        _username.value = username
    }
    fun setPassword(password: String) {
        _password.value = password
    }
    fun login() {
        val username = _username.value ?: ""
        val password = _password.value ?: ""
        if (username.isBlank() || password.isBlank()) {
            _errorMessage.value = "Username and password cannot be empty"
            return
        }
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = ""
                val isValid = authRepository.validateUser(username, password)
                if (isValid) {
                    _loginSuccess.value = true
                } else {
                    _errorMessage.value = "Invalid username or password"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Authentication failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}