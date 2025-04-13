package com.example.bmstu_spotlight.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bmstu_spotlight.saved_locations_screen.presentation.screen.SavedLocationsScreen
import com.example.bmstu_spotlight.schedule_screen.presentation.screen.ScheduleScreen

@Composable
fun BottomNavGraph(
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Location.route) {
            LocationScreen(navController)
        }
        composable(route = BottomBarScreen.SavedLocationsScreen.route) {
            SavedLocationsScreen()
        }
        composable(route = BottomBarScreen.Notifications.route) {
            NotificationsScreen()
        }
        composable(route = BottomBarScreen.Account.route) {
            AccountScreen()
        }
        composable(route = BottomBarScreen.Schedule.route) {
            ScheduleScreen()
        }
    }
}