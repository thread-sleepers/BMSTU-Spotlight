package com.example.bmstu_spotlight.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.bmstu_spotlight.DataHolder
import com.example.bmstu_spotlight.R
import com.example.bmstu_spotlight.Route
import com.example.bmstu_spotlight.ui.theme.ColorBack1
import com.example.bmstu_spotlight.ui.theme.ColorBack2
import com.example.bmstu_spotlight.ui.theme.ColorButton1
import com.example.bmstu_spotlight.ui.theme.ColorButton2
import com.example.bmstu_spotlight.ui.theme.ColorInput1
import com.example.bmstu_spotlight.ui.theme.ColorText2

@Composable
fun LocationScreen(onHomeClicked: () -> Unit,
                   onLocationClicked: () -> Unit,
                   onFavoritesClicked : () -> Unit,
                   onNotificationClicked : () -> Unit,
                   onAccountClicked: () -> Unit) { // Экран отображения маршрута или локации
    val showNewTopSection = remember { mutableStateOf(DataHolder.showNewTopSection) }
    val backgroundImage = painterResource(id = R.drawable.plan)

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
            BottomSection(currentRoute = Route.Location, onHomeClicked = onHomeClicked,
                onLocationClicked = onLocationClicked, onFavoritesClicked = onFavoritesClicked,
                onNotificationClicked = onNotificationClicked, onAccountClicked = onAccountClicked)
        }
    }
}

@Composable //УДАЛИТЬ КОГДА ПОЯВЯТСЯ КАРТЫ!!!
fun InteractiveImageBackground(image: Painter) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val imageSize = remember { mutableStateOf(Size.Zero) }
    val screenSize = remember { mutableStateOf(Size.Zero) }

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
                    // Обновляем масштаб
                    val newScale = (scale * zoom).coerceIn(1f, 3f) // Ограничиваем масштаб от 1x до 3x
                    scale = newScale

                    // Ограничение перемещения
                    val maxX = (imageSize.value.width * scale - screenSize.value.width) / 2
                    val maxY = (imageSize.value.height * scale - screenSize.value.height) / 2

                    offsetX = (offsetX + pan.x).coerceIn(-maxX, maxX)
                    offsetY = (offsetY + pan.y).coerceIn(-maxY, maxY)
                }
            }
    ) {
        // Фоновое изображение
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .onGloballyPositioned { coordinates ->
                    screenSize.value = coordinates.size.toSize()
                    imageSize.value = Size(
                        coordinates.size.width.toFloat(),
                        coordinates.size.height.toFloat()
                    )
                }
        )

        /* Добавление маркеров - проставление меток на карте, закоментировано,
            потому что не настроено, то чтобы маркеры отображались в нужных, а
            не случайных местоах */

        /*DataHolder.markerPositions.forEachIndexed { index, (x, y) ->
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
        }*/
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
