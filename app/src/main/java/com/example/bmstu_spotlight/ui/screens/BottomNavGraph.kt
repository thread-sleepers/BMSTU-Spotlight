package com.example.bmstu_spotlight.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.*
import com.example.bmstu_spotlight.menu_screen.presentation.screen.HomeScreen
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
        startDestination = BottomBarScreen.Home.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Location.route) {
            LocationScreen()
        }
        composable(route = BottomBarScreen.SavedLocationsScreen.route) {
            SavedLocationsScreen()
        }
        composable(route = BottomBarScreen.Account.route) {
           ProfileScreen()
        }
        composable(route = BottomBarScreen.Schedule.route) {
            ScheduleScreen()
        }
    }
}