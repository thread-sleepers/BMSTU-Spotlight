package com.example.bmstu_spotlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bmstu_spotlight.di.appModule
import com.example.bmstu_spotlight.profile.di.ProfileModule
import com.example.bmstu_spotlight.saved_locations_screen.di.locationsModule
import com.example.bmstu_spotlight.schedule_screen.di.scheduleModule
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightTheme
import org.koin.core.context.GlobalContext.startKoin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(appModule)
            modules(locationsModule)
            modules(scheduleModule)
            modules(ProfileModule)
        }
        setContent {
            BMSTUSpotlightTheme {
                BMSTUSpotlightApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMSTUSpotlightTheme {
        BMSTUSpotlightApp()
    }
}
