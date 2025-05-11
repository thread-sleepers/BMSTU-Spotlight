package com.example.bmstu_spotlight.route.di

import com.example.bmstu_spotlight.route.data.repository.GraphRepositoryImpl
import com.example.bmstu_spotlight.route.domain.repository.GraphRepository
import com.example.bmstu_spotlight.route.domain.usecases.FindShortestPathUseCase
import com.example.bmstu_spotlight.route.domain.usecases.FindShortestPathUseCaseImpl
import org.koin.dsl.module

val routeModule = module {
    single<GraphRepository> { GraphRepositoryImpl(get(), get()) }
    factory<FindShortestPathUseCase> { FindShortestPathUseCaseImpl(get()) }
}