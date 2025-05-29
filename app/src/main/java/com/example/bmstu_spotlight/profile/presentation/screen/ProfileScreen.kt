package com.example.bmstu_spotlight.profile.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.bmstu_spotlight.profile.presentation.view_model.ProfileViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bmstu_spotlight.profile.domain.model.UserProfile

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    navController: NavHostController
) {
    val userProfileState by viewModel.userProfile.collectAsState()

    when {
        userProfileState.isLoading -> {
            LoadingView()
        }
        userProfileState.data != null -> {
            ProfileView(
                state = userProfileState.data!!,
                onSignOut = {
                    viewModel.signOut(navController)
                }
            )
        }
        userProfileState.error != null -> {
            ErrorView(onRetry = {
                viewModel.loadUserProfile()
            })
        }
    }
}