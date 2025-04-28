package com.example.bmstu_spotlight.saved_locations_screen.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model.SavedLocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedLocationsScreen(
    viewModel: SavedLocationViewModel = koinViewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val recent by viewModel.recentLocations.collectAsState()
    val favorites by viewModel.favoriteLocations.collectAsState()

    SavedLocationsView(
        recentState = recent,
        favoritesState = favorites,
        onLinkClick = onNavigate
    )
}

