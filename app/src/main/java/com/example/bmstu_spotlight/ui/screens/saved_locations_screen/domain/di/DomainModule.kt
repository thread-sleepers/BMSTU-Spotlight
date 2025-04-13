package com.example.bmstu_spotlight.ui.screens.saved_locations_screen.domain.di

import com.example.bmstu_spotlight.ui.screens.saved_locations_screen.domain.repository.LocationRepository
import com.example.bmstu_spotlight.ui.screens.saved_locations_screen.domain.usecase.GetSavedLocationsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetSavedLocationsUseCase(repository: LocationRepository): GetSavedLocationsUseCase =
        GetSavedLocationsUseCase(repository)
}
