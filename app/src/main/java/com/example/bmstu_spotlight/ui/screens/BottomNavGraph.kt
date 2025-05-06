package com.example.bmstu_spotlight.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.bmstu_spotlight.menu_screen.presentation.screen.MenuScreen
import com.example.bmstu_spotlight.profile.presentation.screen.ProfileScreen
import com.example.bmstu_spotlight.saved_locations_screen.presentation.screen.SavedLocationsScreen
import com.example.bmstu_spotlight.schedule_screen.presentation.screen.ScheduleScreen
import com.example.bmstu_spotlight.location_screen.presentation.screen.LocationScreen


@Composable
fun BottomNavGraph(
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Location.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Home.route) {
            MenuScreen(navController)
        }
        composable(
            route = BottomBarScreen.Location.route + "?mapLink={mapLink}",
            arguments = listOf(
                navArgument("mapLink")
                {
                    type = NavType.StringType
                    nullable = true
                })
        ) { backStackEntry ->
            val mapLink = backStackEntry.arguments?.getString("mapLink")
            LocationScreen(mapLink = mapLink)
        }

        composable(route = BottomBarScreen.SavedLocationsScreen.route) {
            SavedLocationsScreen {
                    link -> navController.navigate(BottomBarScreen.Location.route + "?mapLink=$link")
            }
        }
        composable(route = BottomBarScreen.Account.route) {
           ProfileScreen()
        }
        composable(route = BottomBarScreen.Schedule.route) {
            ScheduleScreen()
        }
    }
}