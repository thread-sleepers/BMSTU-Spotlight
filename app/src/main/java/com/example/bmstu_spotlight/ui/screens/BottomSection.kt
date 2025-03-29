package com.example.bmstu_spotlight.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bmstu_spotlight.Route
import com.example.bmstu_spotlight.ui.theme.ColorBack1

@Composable
fun BottomSection(currentRoute: Route, onHomeClicked: () -> Unit,
                  onLocationClicked: () -> Unit, onFavoritesClicked: () -> Unit,
                  onNotificationClicked: () -> Unit, onAccountClicked: () -> Unit) { // Навигация между экранами (Нижние кнопки)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onHomeClicked,
            enabled = currentRoute != Route.Home
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == Route.Home) Color.White else Color.Unspecified
            )
        }

        IconButton(
            onClick = onLocationClicked ,
            enabled = currentRoute != Route.Location
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Location Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == Route.Location) Color.White else Color.Unspecified
            )
        }

        IconButton(
            onClick = onNotificationClicked,
            enabled = currentRoute != Route.Notifications
        ) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == Route.Notifications) Color.White else Color.Unspecified
            )
        }

        IconButton(
            onClick = onFavoritesClicked ,
            enabled = currentRoute != Route.Favorites
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorites Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == Route.Favorites) Color.White else Color.Unspecified
            )
        }

        IconButton(
            onClick = onAccountClicked ,
            enabled = currentRoute != Route.Account
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Account Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == Route.Account) Color.White else Color.Unspecified
            )
        }
    }
}