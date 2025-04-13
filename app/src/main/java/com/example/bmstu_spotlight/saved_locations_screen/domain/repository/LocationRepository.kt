package com.example.bmstu_spotlight.saved_locations_screen.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getRecentLocations(): Flow<Map<String, String>>
    fun getFavoriteLocations(): Flow<Map<String, String>>
}
