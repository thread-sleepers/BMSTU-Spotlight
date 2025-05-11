package com.example.bmstu_spotlight.location_screen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.bmstu_spotlight.location_screen.presentation.view_model.LocationViewModel
import org.koin.dsl.module

val searchLocationsModule = module {
    viewModel { LocationViewModel(get()) }
}