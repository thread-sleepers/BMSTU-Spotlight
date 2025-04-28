package com.example.bmstu_spotlight.saved_locations_screen.di

import com.example.bmstu_spotlight.saved_locations_screen.data.repository.FakeLocationRepository
import com.example.bmstu_spotlight.saved_locations_screen.domain.repository.LocationRepository
import com.example.bmstu_spotlight.saved_locations_screen.domain.usecase.GetSavedLocationsUseCase
import com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model.SavedLocationViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single<LocationRepository> {
        FakeLocationRepository()
    }
    factory {
        GetSavedLocationsUseCase(get())
    }
    viewModel{
        SavedLocationViewModel(get())
    }
}