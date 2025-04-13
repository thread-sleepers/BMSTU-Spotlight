package com.example.bmstu_spotlight.ui.screens.saved_locations_screen.data.repository

import com.example.bmstu_spotlight.ui.screens.saved_locations_screen.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocationRepository : LocationRepository {
    override fun getRecentLocations(): Flow<Map<String, String>> = flow {
        emit(mapOf(
            "Bauman Racing Team" to "5 мин",
            "Аудитория 395" to "22 мин",
            "Кафе 'Чайная пара'" to "8 мин"
        ))
    }

    override fun getFavoriteLocations(): Flow<Map<String, String>> = flow {
        emit(mapOf(
            "Koнгресс-Холл" to "25 мин",
            "Читальный зал старших крусов" to "18 мин"
        ))
    }
}
