package com.example.bmstu_spotlight.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            HomeScreen()
        }
        composable(route = BottomBarScreen.Location.route) {
            LocationScreen()
        }
        composable(route = BottomBarScreen.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = BottomBarScreen.Notifications.route) {
            NotificationsScreen()
        }
        composable(route = BottomBarScreen.Account.route) {
            AccountScreen()
        }
    }
}