package com.example.bmstu_spotlight.ui.screens

import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.bmstu_spotlight.ui.theme.ColorBack1
import com.example.bmstu_spotlight.ui.theme.ColorBack2
import com.example.bmstu_spotlight.ui.theme.ColorButton1
import com.example.bmstu_spotlight.ui.theme.ColorButton2
import com.example.bmstu_spotlight.ui.theme.ColorInput1
import com.example.bmstu_spotlight.ui.theme.ColorText2
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(navController: NavController, viewModel: LocationViewModel = viewModel()) {
    val showNewTopSection = remember { mutableStateOf(DataHolder.showNewTopSection) }
    val backgroundImage = painterResource(id = R.drawable.plan)

    val selectedNode by viewModel.selectedNode.observeAsState(null)
    val showSheet by viewModel.showSheet.observeAsState(false)

    val scale by viewModel.scale.observeAsState(1f)
    val offsetX by viewModel.offsetX.observeAsState(0f)
    val offsetY by viewModel.offsetY.observeAsState(0f)

    val nodeId = DataHolder.selectedNodeId



    // Вызываем selectNode при загрузке экрана, если nodeId не null
    if (nodeId != null) {
        viewModel.selectNode(nodeId.toString())
    }



    Box(modifier = Modifier.fillMaxSize()) {
        InteractiveImageBackground(image = backgroundImage, scale, offsetX, offsetY, viewModel)

        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (showNewTopSection.value) {
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.90f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ТopSection2(onButtonClick = {
                        DataHolder.showNewTopSection = false
                        showNewTopSection.value = false
                    })
                    RouteBar()
                }
            } else {
                TopSection1 { loc1, loc2 ->
                    DataHolder.location1 = loc1
                    DataHolder.location2 = loc2
                    DataHolder.showNewTopSection = true
                    showNewTopSection.value = true
                }
            }
        }
    }

    Log.d("LocationScreen", "showSheet value: $showSheet")

    if (showSheet) {
        selectedNode?.let { node -> // Проверка на null
            ModalBottomSheet(
                onDismissRequest = { viewModel.closeSheet() },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = ColorBack1,
                scrimColor = Color.Transparent,
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = node.nodeName, // Название узла
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
                    item {
                        Text(
                            text = node.nodeDescription, // Описание узла
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
                    item {
                        Button(
                            modifier = Modifier.fillMaxWidth().height(54.dp).padding(4.dp).shadow(3.dp, shape = CircleShape),
                            colors = ButtonDefaults.buttonColors(containerColor = ColorButton1, contentColor = Color.Black),
                            shape = RoundedCornerShape(28.dp),
                            onClick = { viewModel.closeSheet() }
                        ) {
                            Text("Закрыть", color = ColorText2, fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun InteractiveImageBackground(
    image: Painter,
    scale: Float,
    offsetX: Float,
    offsetY: Float,
    viewModel: LocationViewModel
) {
    val imageSize = remember { mutableStateOf(Size.Zero) }
    val screenSize = remember { mutableStateOf(Size.Zero) }

    // Если выбран маркер, автоматически центрируем карту
    LaunchedEffect(DataHolder.targetMarkerIndex) {
        DataHolder.targetMarkerIndex?.let { index ->
            val (markerX, markerY) = DataHolder.markerPositions[index]
            viewModel.updateScale(DataHolder.targetScale)
            viewModel.updateOffset(-markerX * scale + 500, -markerY * scale + 800)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    viewModel.updateScale((scale * zoom).coerceIn(1f, 3f))
                    viewModel.updateOffset(offsetX + pan.x, offsetY + pan.y)
                }
            }
    ) {
        // Фоновое изображение карты
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

        // Закомментирован код отображения маркеров на карте
        /*
        DataHolder.markerPositions.forEachIndexed { index, (x, y) ->
            Box(
                modifier = Modifier
                    .graphicsLayer(
                        translationX = (x * scale + offsetX).coerceIn(0f, screenSize.value.width.toFloat()),
                        translationY = (y * scale + offsetY).coerceIn(0f, screenSize.value.height.toFloat())
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
        */
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

