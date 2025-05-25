package com.example.bmstu_spotlight.auth_screen.di

import com.example.bmstu_spotlight.auth_screen.data.repository.AuthRepository
import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single {
        AuthRepository(get(), get())
    }
    viewModel {
        AuthViewModel(get(), get())
    }
}