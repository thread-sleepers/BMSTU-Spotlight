package com.example.bmstu_spotlight

import android.app.Application
import com.example.bmstu_spotlight.auth_screen.di.authModule
import com.example.bmstu_spotlight.di.appModule
import com.example.bmstu_spotlight.profile.di.ProfileModule
import com.example.bmstu_spotlight.saved_locations_screen.di.locationsModule
import com.example.bmstu_spotlight.schedule_screen.di.scheduleModule
import com.vk.id.VKID
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.Locale

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
            modules(authModule)
            modules(locationsModule)
            modules(scheduleModule)
            modules(ProfileModule)
        }
        VKID.init(this)
        VKID.instance.setLocale(Locale("ru"))
    }
}