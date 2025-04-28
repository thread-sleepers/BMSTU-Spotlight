package com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.saved_locations_screen.data.repository.LocationDetails
import com.example.bmstu_spotlight.saved_locations_screen.domain.usecase.GetSavedLocationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SavedLocationViewModel(
    private val getSavedLocationsUseCase: GetSavedLocationsUseCase
) : ViewModel() {
    private val _recentLocations = MutableStateFlow<List<LocationDetails>>(emptyList())
    val recentLocations: StateFlow<List<LocationDetails>> = _recentLocations

    private val _favoriteLocations = MutableStateFlow<List<LocationDetails>>(emptyList())
    val favoriteLocations: StateFlow<List<LocationDetails>> = _favoriteLocations

    init {
        viewModelScope.launch {
            val (recent, favorites) = getSavedLocationsUseCase()
            recent.collect { _recentLocations.value = it }
            favorites.collect { _favoriteLocations.value = it }
        }
    }
}