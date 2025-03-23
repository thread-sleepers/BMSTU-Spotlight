package com.example.bmstu_spotlight

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.foundation.background
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.shape.CircleShape
import com.example.bmstu_spotlight.ui.theme.*
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightTheme
import kotlin.random.Random
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.clickable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import android.content.Context
import android.content.Intent





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMSTUSpotlightTheme {
                HomeScreen()
            }
        }
    }
}

enum class Route {
    Home,
    Location,
    Notifications,
    Favorites,
    Account
}


@Composable
fun BottomSection(context: Context, currentRoute: Route) { // Навигация между экранами (Нижние кнопки)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { if (currentRoute != Route.Home) context.startActivity(Intent(context, HomeActivity::class.java)) },
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
            onClick = { if (currentRoute != Route.Location) context.startActivity(Intent(context, LocationActivity::class.java)) },
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
            onClick = { if (currentRoute != Route.Notifications) context.startActivity(Intent(context, NotificationsActivity::class.java)) },
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
            onClick = { if (currentRoute != Route.Favorites) context.startActivity(Intent(context, FavoritesActivity::class.java)) },
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
            onClick = { if (currentRoute != Route.Account) context.startActivity(Intent(context, AccountActivity::class.java)) },
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




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMSTUSpotlightTheme {
        HomeScreen()
    }
}
