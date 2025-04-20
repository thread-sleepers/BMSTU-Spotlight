package com.example.bmstu_spotlight.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route = "Home",
        title = "home",
        icon = Icons.Default.Home
    )
    object Location: BottomBarScreen(
        route = "Location",
        title = "loc",
        icon = Icons.Default.LocationOn
    )
    object Notifications: BottomBarScreen(
        route = "Notifications",
        title = "not",
        icon = Icons.Default.Notifications
    )
    object Account: BottomBarScreen(
        route = "Account",
        title = "acc",
        icon = Icons.Default.AccountCircle
    )
    object SavedLocationsScreen: BottomBarScreen(
        route = "Saved",
        title = "saved",
        icon = Icons.Default.Favorite
    )
    object Schedule: BottomBarScreen(
        route = "Schedule",
        title = "schedule",
        icon = Icons.Default.Star
    )
}