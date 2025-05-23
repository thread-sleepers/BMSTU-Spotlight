package com.example.bmstu_spotlight.ui.screens

import android.view.ViewGroup
import android.webkit.WebView
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
import androidx.compose.ui.viewinterop.AndroidView
import com.example.bmstu_spotlight.DataHolder
import com.example.bmstu_spotlight.R
import com.example.bmstu_spotlight.ui.theme.ColorBack1
import com.example.bmstu_spotlight.ui.theme.ColorBack2
import com.example.bmstu_spotlight.ui.theme.ColorButton1
import com.example.bmstu_spotlight.ui.theme.ColorButton2
import com.example.bmstu_spotlight.ui.theme.ColorInput1
import com.example.bmstu_spotlight.ui.theme.ColorText2

@Composable
fun LocationScreen(mapLink: String = "https://api.maptiler.com/maps/01965777-0fa0-7baa-98d9-d6e9bd013e48/?key=PHHZ2OozEcXHfqqJCqIr#18.77/55.7664093/37.6859631") { // Экран отображения маршрута или локации
    val showNewTopSection = remember { mutableStateOf(DataHolder.showNewTopSection) }

    // Box для наложения элементов экрана поверх карты
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled = true
                }
            },
            update = {
                it.loadUrl(mapLink)
            }
        )
        // Основное содержимое поверх карты
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
                    TopSection2(onButtonClick = {
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
                message_location1.value = newText
            },
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
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorButton1,
                contentColor = Color.Black
            ), // Используем ColorButton1
            shape = RoundedCornerShape(28.dp),
        ) {
            Text("Построить маршрут", color = ColorText2, fontSize = 20.sp)
        }
    }
}

@Composable
fun TopSection2(onButtonClick: () -> Unit) { //Окошко отмены маршрута
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
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorButton2,
                contentColor = Color.Black
            ), // Используем ColorButton2
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
