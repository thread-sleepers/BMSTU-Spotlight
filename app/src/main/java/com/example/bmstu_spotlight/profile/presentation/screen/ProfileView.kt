package com.example.bmstu_spotlight.profile.presentation.screen

//import com.example.bmstu_spotlight.ui.theme.ColorBack3
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmstu_spotlight.R
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
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
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(stringResource(R.string.profile))

        Spacer(modifier = Modifier.height(16.dp))

        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            ProfileHeader(state.name, state.email, state.avatarUrl)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            ProfileField(stringResource(R.string.MGTU_account), state.accountType) {
                Icon(imageVector = Icons.Default.Public, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            ProfileField(stringResource(R.string.language), state.language) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onSignOut,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(stringResource(R.string.go_down), color = MaterialTheme.colorScheme.tertiary)
        }
    }

}