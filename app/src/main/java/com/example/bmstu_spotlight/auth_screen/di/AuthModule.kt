package com.example.bmstu_spotlight.auth_screen.di

import com.example.bmstu_spotlight.auth_screen.data.AuthRepositoryImpl
import com.example.bmstu_spotlight.auth_screen.domain.repository.AuthRepository
import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthViewModel
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import com.example.bmstu_spotlight.schedule_screen.data.repository.FakeScheduleRepository
import com.example.bmstu_spotlight.schedule_screen.domain.repository.ScheduleRepository
import com.example.bmstu_spotlight.schedule_screen.domain.usecase.GetScheduleCase
import com.example.bmstu_spotlight.schedule_screen.presentation.view_model.ScheduleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val authModule = module {
    single { AuthRepositoryImpl(get()) }
    factory<AuthRepository> { (AuthRepositoryImpl(get())) }
    viewModel{
        AuthViewModel(get())
    }
}