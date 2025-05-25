package com.example.bmstu_spotlight.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.bmstu_spotlight.auth_screen.presentation.screen.AuthScreen
import com.example.bmstu_spotlight.menu_screen.presentation.screen.MenuScreen
import com.example.bmstu_spotlight.profile.presentation.screen.ProfileScreen
import com.example.bmstu_spotlight.saved_locations_screen.presentation.screen.SavedLocationsScreen
import com.example.bmstu_spotlight.schedule_screen.presentation.screen.ScheduleScreen
import com.example.bmstu_spotlight.location_screen.presentation.screen.LocationScreen
import com.example.bmstu_spotlight.location_screen.presentation.view_model.LocationViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun BottomNavGraph(
        rootNavController: NavHostController,
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {

    val viewModel: LocationViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Location.route,
        modifier = modifier
    ) {
        composable(route = BottomBarScreen.Home.route) {
            MenuScreen(navController)
        }
        composable(
            route = BottomBarScreen.Location.route + "?locationName={locationName}",
            arguments = listOf(
                navArgument("locationName")
                {
                    type = NavType.StringType
                    nullable = true
                })
        ) { backStackEntry ->
            val locationName = backStackEntry.arguments?.getString("locationName")
            LocationScreen(viewModel = viewModel, locationName = locationName)
        }

        composable(route = BottomBarScreen.SavedLocationsScreen.route) {
            SavedLocationsScreen {
                    name -> navController.navigate(BottomBarScreen.Location.route + "?locationName=$name")
            }
        }
        composable(route = BottomBarScreen.Account.route) {
           ProfileScreen(navController = rootNavController)
        }
        composable(route = BottomBarScreen.Schedule.route) {
            ScheduleScreen{
                    name -> navController.navigate(BottomBarScreen.Location.route + "?locationName=$name")
            }
        }
    }
}