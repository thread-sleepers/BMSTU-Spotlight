package com.example.bmstu_spotlight.saved_locations_screen.data.repository

import com.example.bmstu_spotlight.saved_locations_screen.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocationRepository : LocationRepository {
    override fun getRecentLocations(): Flow<Map<String, LocationDetails>> = flow {
        emit(mapOf(
            "Bauman Racing Team" to LocationDetails("5 мин", "https://api.maptiler.com/maps/01965778-786c-7490-acfc-4186f0cd0a84/?key=PHHZ2OozEcXHfqqJCqIr#18.44/55.7664522/37.6859836"),
            "Аудитория 389" to LocationDetails("22 мин", "https://api.maptiler.com/maps/0196577c-825a-7bf8-ba35-e43d39714f19/?key=PHHZ2OozEcXHfqqJCqIr#17.64/55.766337/37.685957"),
            "Кафе 'Чайная пара'" to LocationDetails("8 мин", "https://api.maptiler.com/maps/01965778-786c-7490-acfc-4186f0cd0a84/?key=PHHZ2OozEcXHfqqJCqIr#18.44/55.7664522/37.6859836")
        ))
    }
    override fun getFavoriteLocations(): Flow<Map<String, LocationDetails>> = flow {
        emit(mapOf(
            "Koнгресс-Холл" to LocationDetails("25 мин", "https://api.maptiler.com/maps/0196577c-825a-7bf8-ba35-e43d39714f19/?key=PHHZ2OozEcXHfqqJCqIr#17.64/55.766337/37.685957"),
            "Аудитория 384" to LocationDetails("18 мин", "https://api.maptiler.com/maps/01965778-786c-7490-acfc-4186f0cd0a84/?key=PHHZ2OozEcXHfqqJCqIr#18.44/55.7664522/37.6859836")
        ))
    }

}