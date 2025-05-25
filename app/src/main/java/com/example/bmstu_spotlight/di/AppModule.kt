package com.example.bmstu_spotlight.di

import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthViewModel
import com.example.bmstu_spotlight.data.datasource.local.db.DatabaseBuilder
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import com.example.bmstu_spotlight.data.repository.EdgeJsonImporter
import com.example.bmstu_spotlight.data.repository.StartupManager
import org.koin.android.ext.koin.androidContext
import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single{
        val baseUrl = "http://10.0.2.2:8080"
        val contentType = "application/json"
        val json = Json {
            ignoreUnknownKeys = true
        }
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                json.asConverterFactory(
                    contentType.toMediaType()
                )
            )
            .build()
            .create(NetworkService::class.java)
    }

    single { AppPreferencesManager(androidContext()) }

    single { DatabaseBuilder(androidContext()) }

    single { get<DatabaseBuilder>().nodeDao }
    single { get<DatabaseBuilder>().edgeDao }
    single { get<DatabaseBuilder>().floorDao }
    single { get<DatabaseBuilder>().buildingDao }

    single { EdgeJsonImporter(androidContext(), get(), get(), get(), get()) }
    single { StartupManager(get(), get()) }

    viewModel {
        AuthViewModel(get(), get())
    }

}