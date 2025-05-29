package com.example.bmstu_spotlight.di

import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthViewModel
import com.example.bmstu_spotlight.data.datasource.local.db.DatabaseBuilder
import com.example.bmstu_spotlight.data.datasource.remote.AuthInterceptor
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import com.example.bmstu_spotlight.data.repository.EdgeJsonImporter
import com.example.bmstu_spotlight.data.repository.StartupManager
import org.koin.android.ext.koin.androidContext
import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { AppPreferencesManager(androidContext()) }

    single { AuthInterceptor(get()) }

    single{
        val baseUrl = "http://10.0.2.2:8080"
        val contentType = "application/json"
        val json = Json {
            ignoreUnknownKeys = true
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory(
                    contentType.toMediaType()
                )
            )
            .build()
            .create(NetworkService::class.java)
    }

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