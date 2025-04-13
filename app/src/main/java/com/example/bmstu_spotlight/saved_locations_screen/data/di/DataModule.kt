package com.example.bmstu_spotlight.saved_locations_screen.data.di

import com.example.bmstu_spotlight.saved_locations_screen.data.repository.FakeLocationRepository
import com.example.bmstu_spotlight.saved_locations_screen.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideLocationRepository(): LocationRepository = FakeLocationRepository()
}
