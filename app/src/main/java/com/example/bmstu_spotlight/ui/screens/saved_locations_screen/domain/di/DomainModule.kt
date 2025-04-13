package com.example.bmstu_spotlight.ui.screens.saved_locations_screen.domain.di

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
