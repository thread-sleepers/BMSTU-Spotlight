package com.example.bmstu_spotlight.profile.di

import com.example.bmstu_spotlight.profile.data.repository.ProfileRepositoryImpl
import com.example.bmstu_spotlight.profile.domain.repository.ProfileRepository
import com.example.bmstu_spotlight.profile.presentation.view_model.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {

    single<ProfileRepository> { ProfileRepositoryImpl(get()) }

    viewModel { ProfileViewModel(get(), get()) }
}