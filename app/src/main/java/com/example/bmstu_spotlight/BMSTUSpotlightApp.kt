package com.example.bmstu_spotlight

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bmstu_spotlight.ui.screens.BottomBarScreen
import com.example.bmstu_spotlight.ui.screens.BottomNavGraph
import com.example.bmstu_spotlight.ui.theme.ColorBack1

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BMSTUSpotlightApp(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                modifier = Modifier.systemBarsPadding()
            )
        }
    ) {
        BottomNavGraph(
            navController = navController,
            modifier = Modifier.systemBarsPadding()
        )
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Location,
        BottomBarScreen.Account,
        BottomBarScreen.SavedLocationsScreen,
        BottomBarScreen.Schedule
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color.Transparent,
        modifier = modifier
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen, currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        modifier = Modifier.background(ColorBack1, shape = RoundedCornerShape(18.dp)),
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                modifier = Modifier.size(40.dp),
                tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) Color.White else Color.Unspecified)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.high),
        selectedContentColor = Color.White,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}