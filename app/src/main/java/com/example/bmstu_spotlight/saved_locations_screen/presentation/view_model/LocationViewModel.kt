package com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.saved_locations_screen.data.repository.LocationDetails
import com.example.bmstu_spotlight.saved_locations_screen.domain.usecase.GetSavedLocationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel(
    private val getSavedLocationsUseCase: GetSavedLocationsUseCase
) : ViewModel() {
    private val _recentLocations = MutableStateFlow<Map<String, LocationDetails>>(emptyMap())
    val recentLocations: StateFlow<Map<String, LocationDetails>> = _recentLocations

    private val _favoriteLocations = MutableStateFlow<Map<String, LocationDetails>>(emptyMap())
    val favoriteLocations: StateFlow<Map<String, LocationDetails>> = _favoriteLocations

    init {
        viewModelScope.launch {
            val (recent, favorites) = getSavedLocationsUseCase()
            recent.collect { _recentLocations.value = it }
            favorites.collect { _favoriteLocations.value = it }
        }
    }
}