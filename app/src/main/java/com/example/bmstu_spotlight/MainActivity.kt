package com.example.bmstu_spotlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.foundation.background
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.CircleShape
import com.example.bmstu_spotlight.ui.theme.*
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightAppNewTheme
import kotlin.random.Random
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.clickable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bmstu_spotlight.auth_screen.di.authModule
import com.example.bmstu_spotlight.data.repository.AppPreferencesManager
import com.example.bmstu_spotlight.di.appModule
import com.example.bmstu_spotlight.profile.di.ProfileModule
import com.example.bmstu_spotlight.saved_locations_screen.di.locationsModule
import com.example.bmstu_spotlight.schedule_screen.di.scheduleModule
import com.example.bmstu_spotlight.ui.screens.BottomBarScreen
import com.vk.id.VKID
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.startKoin
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var startDestination: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref: AppPreferencesManager by inject()

        lifecycleScope.launch {
            val isAuth = pref.isAuthenticated.first()
            startDestination = if (isAuth) {
                BottomBarScreen.Location.route
            } else {
                BottomBarScreen.Auth.route
            }

            setContent {
                BMSTUSpotlightAppNewTheme {
                    BMSTUSpotlightApp(startDestination)
                }
            }
        }
    }
}
//class MainActivity : ComponentActivity() {
//    private lateinit var startDestination: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val pref: AppPreferencesManager by inject()
//
//        runBlocking {
//            pref.isAuthenticated.collect { isAuth ->
//                if (isAuth) {
//                    startDestination = BottomBarScreen.Location.route
//                } else {
//                    startDestination = BottomBarScreen.Auth.route
//                }
//                return@collect
//            }
//            return@runBlocking
//        }
//        setContent {
//            BMSTUSpotlightAppNewTheme {
//                BMSTUSpotlightApp(startDestination)
//            }
//        }
//    }
//}



//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    BMSTUSpotlightAppNewTheme {
//        BMSTUSpotlightApp()
//    }
//}

