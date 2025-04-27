package com.example.bmstu_spotlight.saved_locations_screen.domain.repository

import com.example.bmstu_spotlight.saved_locations_screen.data.repository.LocationDetails
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getRecentLocations(): Flow<Map<String, LocationDetails>>
    fun getFavoriteLocations(): Flow<Map<String, LocationDetails>>
}
