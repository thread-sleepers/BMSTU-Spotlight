package com.example.bmstu_spotlight.profile.presentation.screen

import com.example.bmstu_spotlight.ui.theme.ColorBack3
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bmstu_spotlight.profile.domain.model.UserProfile
import com.example.bmstu_spotlight.profile.presentation.view_model.ProfileViewModel
import com.example.bmstu_spotlight.profile.presentation.components.ProfileField
import com.example.bmstu_spotlight.profile.presentation.components.ProfileHeader

@Composable
fun ProfileView(
    state: UserProfile,
    onSignOut: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBack3)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Профиль",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            ProfileHeader(state.name, state.email, state.avatarUrl)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            ProfileField("МГТУ Аккаунт", state.accountType) {
                Icon(imageVector = Icons.Default.Public, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            ProfileField("Язык", state.language) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onSignOut,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Выйти", color = Color.Red)
        }
    }

}