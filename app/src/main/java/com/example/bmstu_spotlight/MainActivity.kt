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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMSTUSpotlightTheme {
                MyApp()
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            NavHost(navController = navController, startDestination = "location") {
                composable("home") { HomeScreen(navController) }
                composable("location") { LocationScreen(navController) }
                composable("notifications") { NotificationsScreen(navController) }
                composable("favorites") { FavoritesScreen(navController) }
                composable("account") { AccountScreen(navController) }
            }
        }
    )
}

@Composable
fun LocationScreen(navController: NavHostController) {

    val showNewTopSection = remember { mutableStateOf(false) }
    val location1 = remember { mutableStateOf("") }
    val location2 = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (showNewTopSection.value) {
            ТopSection2(
                onButtonClick = { showNewTopSection.value = false }
            )
            CenterSection()
            Spacer(modifier = Modifier.weight(1f))// добавляет пустое пространство
            RouteBar()

        } else {
            TopSection1(
                onButtonClick = { message_location1, message_location2 ->
                    location1.value = message_location1
                    location2.value = message_location2
                    showNewTopSection.value = true
                    // Передаем переменные, как вам нужно
                    // например, вы можете использовать navController.navigate("route/${location1}/${location2}")
                }
            )
            CenterSection()
        }

        BottomSection(navController, currentRoute = "location")
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Menu Screen", fontSize = 28.sp)
        BottomSection(navController, currentRoute = "home")
    }
}

@Composable
fun NotificationsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Notifications Screen", fontSize = 28.sp)
        BottomSection(navController, currentRoute = "notifications")
    }
}

@Composable
fun FavoritesScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Favorites Screen", fontSize = 28.sp)
        BottomSection(navController, currentRoute = "favorites")
    }
}

@Composable
fun AccountScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Account Screen", fontSize = 28.sp)
        BottomSection(navController, currentRoute = "account")

    }
}

@Composable
fun TopSection1(onButtonClick: (String, String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp)) // Используем ColorBack1
            .padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        val message_location1 = remember { mutableStateOf("") }
        val message_location2 = remember { mutableStateOf("") }

        OutlinedTextField(
            value = message_location1.value,
            onValueChange = { newText -> message_location1.value = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(ColorInput1, shape = RoundedCornerShape(28.dp)), // Используем ColorInput1
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = { Text("Откуда", fontSize = 20.sp) },
            singleLine = true,
            shape = RoundedCornerShape(28.dp),
        )

        OutlinedTextField(
            value = message_location2.value,
            onValueChange = { newText -> message_location2.value = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(ColorInput1, shape = RoundedCornerShape(28.dp)), // Используем ColorInput1
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = { Text("Куда", fontSize = 20.sp) },
            singleLine = true,
            shape = RoundedCornerShape(28.dp),
        )

        Button(
            onClick = { onButtonClick(message_location1.value, message_location2.value) },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(4.dp)
                .shadow(3.dp, shape = CircleShape),
            colors = ButtonDefaults.buttonColors(containerColor = ColorButton1, contentColor = Color.Black), // Используем ColorButton1
            shape = RoundedCornerShape(28.dp),
        ) {
            Text("Построить маршрут", color = ColorText2, fontSize = 20.sp) // Используем ColorText2
        }
    }
}

@Composable
fun ТopSection2(onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp)) // Используем ColorBack1
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onButtonClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(4.dp)
                .shadow(3.dp, shape = CircleShape),
            colors = ButtonDefaults.buttonColors(containerColor = ColorButton2, contentColor = Color.Black), // Используем ColorButton2
            shape = RoundedCornerShape(28.dp),
        ) {
            Text("Новый маршрут", color = ColorText2, fontSize = 20.sp) // Используем ColorText2
        }
    }
}


@Composable
fun CenterSection() {
    // Оставлено пустым для примера
}

@Composable
fun RouteBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(ColorBack2, shape = RoundedCornerShape(18.dp)) // Используем ColorBack2
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Walk straight for 5 min",
            modifier = Modifier.fillMaxWidth(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun BottomSection(navController: NavHostController, currentRoute: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp)) // Используем ColorBack1
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { if (currentRoute != "home") navController.navigate("home") },
            enabled = currentRoute != "home"
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == "home") Color.White else Color.Unspecified
            )
        }

        IconButton(
            onClick = { if (currentRoute != "location") navController.navigate("location") },
            enabled = currentRoute != "location"
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Location Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == "location") Color.White else Color.Unspecified
            )
        }

        IconButton(
            onClick = { if (currentRoute != "notifications") navController.navigate("notifications") },
            enabled = currentRoute != "notifications"
        ) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Bell Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == "notifications") Color.White else Color.Unspecified
            )
        }

        IconButton(
            onClick = { if (currentRoute != "favorites") navController.navigate("favorites") },
            enabled = currentRoute != "favorites"
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == "favorites") Color.White else Color.Unspecified
            )
        }

        IconButton(
            onClick = { if (currentRoute != "account") navController.navigate("account") },
            enabled = currentRoute != "account"
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Account Icon",
                modifier = Modifier.size(80.dp),
                tint = if (currentRoute == "account") Color.White else Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMSTUSpotlightTheme {
        MyApp()
    }
}
