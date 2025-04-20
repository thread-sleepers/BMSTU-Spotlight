package com.example.bmstu_spotlight.profile.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.bmstu_spotlight.profile.presentation.view_model.ProfileViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val userProfile by viewModel.userProfile.collectAsState()

    userProfile?.let { profile ->
        ProfileView(
            state = profile,
            onSignOut = {
                viewModel.signOut()
            }
        )
    }
}