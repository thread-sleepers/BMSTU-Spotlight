package com.example.bmstu_spotlight.profile.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmstu_spotlight.profile.data.ProfileRepositoryImpl
import com.example.bmstu_spotlight.profile.domain.repository.ProfileRepository
import com.example.bmstu_spotlight.profile.presentation.view_model.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ProfileModule = module {

    single<ProfileRepository> { ProfileRepositoryImpl() }

    viewModel { ProfileViewModel(get()) }
}