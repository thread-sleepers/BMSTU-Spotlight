package com.example.bmstu_spotlight.di

import com.example.bmstu_spotlight.data.datasource.remote.NetworkService
import com.example.bmstu_spotlight.schedule_screen.domain.repository.ScheduleRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
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
    }

    single<NetworkService> {
        get<Retrofit>().create(NetworkService::class.java)
    }
}