package com.example.bmstu_spotlight



import android.annotation.SuppressLint

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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.*

import androidx.compose.ui.draw.scale
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource


class LocationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMSTUSpotlightTheme {
                LocationScreen()

            }
        }
    }
}

@Composable
fun LocationScreen() { // Экран отображения маршрута или локации
    val showNewTopSection = remember { mutableStateOf(DataHolder.showNewTopSection) }
    val backgroundImage = painterResource(id = R.drawable.ic_launcher_background)

    // Box для наложения элементов друг на друга
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Интерактивное фоновое изображение
        InteractiveImageBackground(image = backgroundImage)

        // Основное содержимое поверх фонового изображения
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (showNewTopSection.value) { // Когда маршрут начат
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.90f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ТopSection2(onButtonClick = {
                        DataHolder.showNewTopSection = false // Сохранение в DataHolder
                        showNewTopSection.value = false
                    })
                    RouteBar()
                }
            } else { // Когда маршрут ещё не начат
                TopSection1 { loc1, loc2 ->
                    DataHolder.location1 = loc1 // Сохранение данных в DataHolder
                    DataHolder.location2 = loc2
                    DataHolder.showNewTopSection = true
                    showNewTopSection.value = true
                }
            }
            BottomSection(context = LocalContext.current, currentRoute = Route.Location)
        }
    }
}

@Composable //УДАЛИТЬ КОГДА ПОЯВЯТСЯ КАРТЫ!!!
fun InteractiveImageBackground(image: Painter) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    // Если выбран маркер, автоматически приближаемся к нему
    LaunchedEffect(DataHolder.targetMarkerIndex) {
        DataHolder.targetMarkerIndex?.let { index ->
            val (markerX, markerY) = DataHolder.markerPositions[index]
            scale = DataHolder.targetScale
            offsetX = -markerX * scale + 500 // Центрируем маркер на экране (500 — примерный экранный центр по X)
            offsetY = -markerY * scale + 800 // Центрируем маркер на экране (800 — примерный экранный центр по Y)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    offsetX += pan.x
                    offsetY += pan.y
                }
            }
    ) {
        // Фоновое изображение
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
        )

        // Добавление маркеров
        DataHolder.markerPositions.forEachIndexed { index, (x, y) ->
            Box(
                modifier = Modifier
                    .graphicsLayer(
                        translationX = x * scale + offsetX,
                        translationY = y * scale + offsetY
                    )
                    .size(40.dp)
                    .background(color = Color.Red, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (index + 1).toString(),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}






@Composable
fun TopSection1(onButtonClick: (String, String) -> Unit) { //Окошко ввода начальной и конечной локации
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        val message_location1 = remember { mutableStateOf("") }
        val message_location2 = remember { mutableStateOf("") }

        OutlinedTextField(
            value = message_location1.value,
            onValueChange = { newText ->
                message_location1.value = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(ColorInput1, shape = RoundedCornerShape(28.dp)),
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = { Text("Откуда", fontSize = 20.sp) },
            singleLine = true,
            shape = RoundedCornerShape(28.dp),
        )

        OutlinedTextField(
            value = message_location2.value,
            onValueChange = { newText ->
                message_location2.value = newText
                // Проверяем, соответствует ли ввод имени маркера УДАЛИТЬ КОГДА ПОЯВЯТСЯ КАРТЫ!!!
                when (newText) {
                    "1" -> DataHolder.targetMarkerIndex = 0
                    "2" -> DataHolder.targetMarkerIndex = 1
                    "3" -> DataHolder.targetMarkerIndex = 2
                    "4" -> DataHolder.targetMarkerIndex = 3
                    else -> DataHolder.targetMarkerIndex = null
                }//УДАЛИТЬ КОГДА ПОЯВЯТСЯ КАРТЫ!!!
                },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(ColorInput1, shape = RoundedCornerShape(28.dp)),
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
            Text("Построить маршрут", color = ColorText2, fontSize = 20.sp)
        }
    }
}

@Composable
fun ТopSection2(onButtonClick: () -> Unit) { //Окошко отмены маршрута
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp))
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
            Text("Новый маршрут", color = ColorText2, fontSize = 20.sp)
        }
    }
}

@Composable
fun CenterSection() {

}

@Composable
fun RouteBar() { //Окошко с временем маршрута
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(ColorBack2, shape = RoundedCornerShape(18.dp))
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
