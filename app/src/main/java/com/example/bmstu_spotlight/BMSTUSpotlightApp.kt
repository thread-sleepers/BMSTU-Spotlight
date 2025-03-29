package com.example.bmstu_spotlight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bmstu_spotlight.ui.screens.AccountScreen
import com.example.bmstu_spotlight.ui.screens.FavoritesScreen
import com.example.bmstu_spotlight.ui.screens.HomeScreen
import com.example.bmstu_spotlight.ui.screens.LocationScreen
import com.example.bmstu_spotlight.ui.screens.NotificationsScreen

enum class Route() {
    Home,
    Location,
    Notifications,
    Favorites,
    Account
}

@Composable
fun BMSTUSpotlightApp(navController: NavHostController = rememberNavController())
{
    Scaffold() { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Route.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = Route.Home.name) {
                HomeScreen(
                    onHomeClicked = {navController.navigate(Route.Home.name)},
                    onLocationClicked = {navController.navigate(Route.Location.name)},
                    onFavoritesClicked = {navController.navigate(Route.Favorites.name)},
                    onAccountClicked = {navController.navigate(Route.Account.name)},
                    onNotificationClicked = {navController.navigate(Route.Notifications.name)}
                )
            }
            composable(route = Route.Location.name) {
                LocationScreen(
                    onHomeClicked = {navController.navigate(Route.Home.name)},
                    onLocationClicked = {navController.navigate(Route.Location.name)},
                    onFavoritesClicked = {navController.navigate(Route.Favorites.name)},
                    onAccountClicked = {navController.navigate(Route.Account.name)},
                    onNotificationClicked = {navController.navigate(Route.Notifications.name)}
                )
            }
            composable(route = Route.Favorites.name) {
                FavoritesScreen(
                    onHomeClicked = {navController.navigate(Route.Home.name)},
                    onLocationClicked = {navController.navigate(Route.Location.name)},
                    onFavoritesClicked = {navController.navigate(Route.Favorites.name)},
                    onAccountClicked = {navController.navigate(Route.Account.name)},
                    onNotificationClicked = {navController.navigate(Route.Notifications.name)}
                )
            }
            composable(route = Route.Notifications.name) {
                NotificationsScreen(
                    onHomeClicked = {navController.navigate(Route.Home.name)},
                    onLocationClicked = {navController.navigate(Route.Location.name)},
                    onFavoritesClicked = {navController.navigate(Route.Favorites.name)},
                    onAccountClicked = {navController.navigate(Route.Account.name)},
                    onNotificationClicked = {navController.navigate(Route.Notifications.name)}
                )
            }
            composable(route = Route.Account.name) {
                AccountScreen(
                    onHomeClicked = {navController.navigate(Route.Home.name)},
                    onLocationClicked = {navController.navigate(Route.Location.name)},
                    onFavoritesClicked = {navController.navigate(Route.Favorites.name)},
                    onAccountClicked = {navController.navigate(Route.Account.name)},
                    onNotificationClicked = {navController.navigate(Route.Notifications.name)}
                )
            }
        }
    }
}