package com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.saved_locations_screen.domain.usecase.GetSavedLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationViewModel : ViewModel() {
    private val _recentLocations = MutableStateFlow<Map<String, String>>(emptyMap())
    val recentLocations: StateFlow<Map<String, String>> = _recentLocations

    private val _favoriteLocations = MutableStateFlow<Map<String, String>>(emptyMap())
    val favoriteLocations: StateFlow<Map<String, String>> = _favoriteLocations

    init {
        // Initialize with default values
        _recentLocations.value = mapOf(
            "Bauman Racing Team" to "5 мин",
            "Аудитория 395" to "22 мин",
            "Кафе 'Чайная пара'" to "8 мин"
        )

        _favoriteLocations.value = mapOf(
            "Koнгресс-Холл" to "25 мин",
            "Читальный зал старших крусов" to "18 мин"
        )
    }
}
//@HiltViewModel
//class LocationViewModel @Inject constructor(
//    private val getSavedLocationsUseCase: GetSavedLocationsUseCase
//) : ViewModel() {
//    private val _recentLocations = MutableStateFlow<Map<String, String>>(emptyMap())
//    val recentLocations: StateFlow<Map<String, String>> = _recentLocations
//
//    private val _favoriteLocations = MutableStateFlow<Map<String, String>>(emptyMap())
//    val favoriteLocations: StateFlow<Map<String, String>> = _favoriteLocations
//
//    init {
//        loadLocations()
//    }
//
//    private fun loadLocations() {
//        viewModelScope.launch {
//            val (recent, favorites) = getSavedLocationsUseCase()
//            recent.collect { _recentLocations.value = it }
//            favorites.collect { _favoriteLocations.value = it }
//        }
//    }
//}
