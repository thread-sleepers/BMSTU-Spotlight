package com.example.bmstu_spotlight.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.Route

@Composable
fun AccountScreen(onHomeClicked: () -> Unit,
                  onLocationClicked: () -> Unit,
                  onFavoritesClicked : () -> Unit,
                  onNotificationClicked : () -> Unit,
                  onAccountClicked: () -> Unit) { // Экран аккаунта
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Account Screen", fontSize = 28.sp)
        BottomSection(currentRoute = Route.Account, onHomeClicked = onHomeClicked,
            onLocationClicked = onLocationClicked, onFavoritesClicked = onFavoritesClicked,
            onNotificationClicked = onNotificationClicked, onAccountClicked = onAccountClicked)
    }

}

