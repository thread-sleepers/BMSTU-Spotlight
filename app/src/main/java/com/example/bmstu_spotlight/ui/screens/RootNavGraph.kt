package com.example.bmstu_spotlight.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bmstu_spotlight.BMSTUSpotlightApp
import com.example.bmstu_spotlight.auth_screen.presentation.screen.AuthScreen
import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthState
import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier
) {
    val authViewModel: AuthViewModel = koinViewModel()
    val navController = rememberNavController()
    val authState = authViewModel.authState.collectAsState()
    val startDestination = if (authState.equals(AuthState.AUTHENTICATED)) {
        RootScreen.MainApp.route
    } else {
        RootScreen.Auth.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(RootScreen.Auth.route) {
            AuthScreen(
                onLoginSuccessful = {
                    navController.navigate(RootScreen.MainApp.route) {
                        popUpTo(RootScreen.Auth.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(RootScreen.MainApp.route) {
            BMSTUSpotlightApp()
        }
    }
}