package com.example.bmstu_spotlight.auth_screen.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(onLoginSuccessful: () -> Unit) {
    val viewModel: AuthViewModel = koinViewModel()
    val state by viewModel.authState.collectAsState()
    val onLogin = { email: String, password: String ->
        viewModel.login(email, password)
    }
    AuthView(
        state = state,
        onLogin = onLogin,
        onLoginSuccessful = onLoginSuccessful
    )
}