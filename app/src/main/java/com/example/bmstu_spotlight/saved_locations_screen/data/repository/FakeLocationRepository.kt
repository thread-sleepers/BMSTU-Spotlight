package com.example.bmstu_spotlight.saved_locations_screen.data.repository

import com.example.bmstu_spotlight.saved_locations_screen.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocationRepository : LocationRepository {
    override fun getRecentLocations(): Flow<List<LocationDetails>> = flow {
        emit(
            listOf(
                LocationDetails(
                    "Bauman Racing Team",
                    "5 мин",
                    "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635"
                ),
                LocationDetails(
                    "389",
                    "22 мин",
                    "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604"
                ),
                LocationDetails(
                    "Кафе 'Чайная пара'",
                    "8 мин",
                    "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635"
                )
            )
        )
    }

    override fun getFavoriteLocations(): Flow<List<LocationDetails>> = flow {
        emit(
            listOf(
                LocationDetails(
                    "Koнгресс-Холл",
                    "25 мин",
                    "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604"
                ),
                LocationDetails(
                    "384",
                    "18 мин",
                    "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635"
                )
            )
        )
    }

}