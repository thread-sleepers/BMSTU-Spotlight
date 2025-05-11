package com.example.bmstu_spotlight

import android.app.Application
import com.example.bmstu_spotlight.data.repository.StartupManager
import com.example.bmstu_spotlight.di.appModule
import com.example.bmstu_spotlight.location_screen.di.searchLocationsModule
import com.example.bmstu_spotlight.profile.di.profileModule
import com.example.bmstu_spotlight.route.di.routeModule
import com.example.bmstu_spotlight.saved_locations_screen.di.locationsModule
import com.example.bmstu_spotlight.schedule_screen.di.scheduleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BMSTUSpotlightApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val allModules = listOf(
            appModule,
            searchLocationsModule,
            locationsModule,
            scheduleModule,
            profileModule,
            routeModule
        )

        startKoin {
            androidContext(this@BMSTUSpotlightApplication)
            modules(allModules)
        }

        val startupManager: StartupManager = org.koin.java.KoinJavaComponent.get(StartupManager::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            startupManager.performInitialIfNeeded()
        }
    }
}