package com.example.bmstu_spotlight.auth_screen.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthViewModel
import com.example.bmstu_spotlight.saved_locations_screen.presentation.screen.SavedLocationsView
import com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model.SavedLocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
) {
    val viewModel: AuthViewModel = koinViewModel()
    AuthView(
        viewModel = viewModel
    )
}