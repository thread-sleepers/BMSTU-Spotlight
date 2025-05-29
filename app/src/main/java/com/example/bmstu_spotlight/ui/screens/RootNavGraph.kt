package com.example.bmstu_spotlight.ui.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bmstu_spotlight.BMSTUSpotlightApp
import com.example.bmstu_spotlight.R
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
    val context = LocalContext.current

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
                    Toast.makeText(
                        context,
                        context.getString(R.string.log_in_is_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
        composable(RootScreen.MainApp.route) {
            BMSTUSpotlightApp(rootNavController = navController)
        }
    }
}