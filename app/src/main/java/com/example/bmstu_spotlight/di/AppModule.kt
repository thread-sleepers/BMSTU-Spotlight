package com.example.bmstu_spotlight.di

import com.example.bmstu_spotlight.data.datasource.local.db.DatabaseBuilder
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import com.example.bmstu_spotlight.data.repository.EdgeJsonImporter
import com.example.bmstu_spotlight.data.repository.StartupManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
//    single<NetworkService> {
//        RetrofitBuilder()
//    }

    single { AppPreferencesManager(androidContext()) }

    single { DatabaseBuilder(androidContext()) }

    single { get<DatabaseBuilder>().nodeDao }
    single { get<DatabaseBuilder>().edgeDao }
    single { get<DatabaseBuilder>().floorDao }
    single { get<DatabaseBuilder>().buildingDao }

    single { EdgeJsonImporter(androidContext(), get(), get(), get(), get()) }
    single { StartupManager(get(), get()) }

}