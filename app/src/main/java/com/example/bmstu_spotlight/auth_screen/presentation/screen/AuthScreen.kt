package com.example.bmstu_spotlight.auth_screen.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bmstu_spotlight.saved_locations_screen.presentation.screen.SavedLocationsView
import com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model.SavedLocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedLocationsScreen(
    viewModel: SavedLocationViewModel = koinViewModel()
) {
    //val user by viewModel..collectAsState()
    //val password by viewModel..collectAsState()

    AuthView(
        //user = recent,
        //password = favorites
    )
}