package com.example.bmstu_spotlight.saved_locations_screen.domain.usecase

import com.example.bmstu_spotlight.saved_locations_screen.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetSavedLocationsUseCase(
    private val repository: LocationRepository
) {
    operator fun invoke(): Pair<Flow<Map<String, String>>, Flow<Map<String, String>>> {
        return Pair(repository.getRecentLocations(), repository.getFavoriteLocations())
    }
}