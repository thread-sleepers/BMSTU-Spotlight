package com.example.bmstu_spotlight.di

import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { AppPreferencesManager(androidContext()) }
}