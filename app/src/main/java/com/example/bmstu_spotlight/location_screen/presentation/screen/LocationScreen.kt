package com.example.bmstu_spotlight.location_screen.presentation.screen

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.bmstu_spotlight.DataHolder
import com.example.bmstu_spotlight.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.RectangleShape
import androidx.lifecycle.Lifecycle
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.bmstu_spotlight.location_screen.data.popularFrom
import com.example.bmstu_spotlight.location_screen.data.popularTo
import com.example.bmstu_spotlight.location_screen.presentation.view_model.LocationState
import com.example.bmstu_spotlight.location_screen.presentation.view_model.LocationViewModel
import com.example.bmstu_spotlight.ui.helper_functions.findLocationLink
import com.example.bmstu_spotlight.ui.helper_functions.findLocationName
import com.example.bmstu_spotlight.ui.helper_functions.findRoute
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
import com.example.bmstu_spotlight.ui.helper_functions.find2Locations
import com.example.bmstu_spotlight.ui.helper_functions.findLinkLocation
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightAppNewTheme
import com.example.bmstu_spotlight.ui.helper_functions.findLocationFloor
import kotlin.math.floor

import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(viewModel: LocationViewModel = koinViewModel(), mapLink: String?) {
    val uiState by viewModel.uiState.collectAsState()
    var currentMapLink by remember { mutableStateOf(mapLink) }

    LaunchedEffect(mapLink, uiState.currentFloor) {
        if(currentMapLink != null) {
            val loc3 = findLinkLocation(mapLink.toString()) // находит адуиторию по ссылке
            viewModel.updateFloor(findLocationFloor(loc3.toString()))
            viewModel.updateMessageLocation2(loc3.toString())
            viewModel.updateMapLink(currentMapLink, uiState.currentFloor)
            currentMapLink = null // исправил баг с аудиторией из избранных
        }
        else{
           viewModel.updateMapLink(uiState.currentMapLink, uiState.currentFloor)
        }
    }

    // Box для наложения элементов экрана поверх карты
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)

    ) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                    settings.javaScriptEnabled = true
                    loadUrl(uiState.currentMapLink)

                }
            },
            update = {
                it.loadUrl(uiState.currentMapLink)
            }
        )
        // Основное содержимое поверх карты
        CustomTopBar(stringResource(R.string.navigator))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (uiState.showNewTopSection) {
                TopSection2(onButtonClick = {
                    viewModel.toggleTopSection(false)
                    uiState.messageLocation1 = ""
                    uiState.messageLocation2 = ""
                    viewModel.updaten1Floor(6)
                    viewModel.updaten2Floor(6)
                })
                RouteBar()
            } else { // Когда маршрут ещё не начат
                TopSection1(
                    from = uiState.messageLocation1,
                    to = uiState.messageLocation2,
                    onFromChange = {
                        viewModel.updateMessageLocation1(it)
                        viewModel.updateFloor(findLocationFloor(it))
                    },
                    onToChange = {
                        viewModel.updateMessageLocation2(it)
                        viewModel.updateFloor(findLocationFloor(it))
                    },
                    onButtonClick = { loc1, loc2 ->
                        uiState.messageLocation1 = loc1
                        uiState.messageLocation2 = loc2
                        DataHolder.location1 = loc1 // Сохранение данных в DataHolder
                        DataHolder.location2 = loc2
                        viewModel.findRoute(loc1, loc2)
                        viewModel.toggleTopSection(true)
                        viewModel.updateMapLink(findRoute(loc1, loc2, uiState.currentFloor), uiState.currentFloor)
                        viewModel.updaten1Floor(findLocationFloor(loc1))
                        viewModel.updaten2Floor(findLocationFloor(loc2))
                    },
                    onEnterLink = {
                        link ->
                        viewModel.updateMapLink(link,uiState.currentFloor)

                    },
                    onEnterFloor = {
                        floor ->
                        viewModel.updateFloor(floor)
                    }
                )
            }


            FloorsColumn(
                clickedFloor = uiState.currentFloor,
                needFloor1 = uiState.needFloor1,
                needFloor2 = uiState.needFloor2,
                onFloorClick = { floor ->
                    viewModel.updateFloor(floor)
                    viewModel.updateMapLink(findRoute(uiState.messageLocation1, uiState.messageLocation2, floor), floor)
                })


        }
    }

    LaunchedEffect(Unit) {
        DataHolder.selectedNodeId?.let { nodeId ->
            viewModel.selectNode(nodeId)
        }
    }

    if (uiState.showSheet) {
        uiState.selectedNode?.let { node ->
           viewModel.updateMessageLocation2(node.name)

            ModalBottomSheet(
                onDismissRequest = { viewModel.closeSheet() },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                scrimColor = Color.Transparent,
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(text = node.name, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                    }
                    item {
                        Text(text = node.description, fontSize = 20.sp, textAlign = TextAlign.Center)
                    }
                    item {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .padding(4.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            shape = RoundedCornerShape(28.dp),
                            onClick = { viewModel.closeSheet() }
                        ) {
                            Text(text = stringResource(R.string.close), fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopSection1(
    from: String,
    to: String,
    onFromChange: (String) -> Unit,
    onToChange: (String) -> Unit,
    onButtonClick: (String, String) -> Unit,
    onEnterLink: (String?) -> Unit,
    onEnterFloor:(Int)-> Unit
) { //Окошко ввода начальной и конечной локации
    val showSuggestionsFrom = remember { mutableStateOf(false) }
    val showSuggestionsTo = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(18.dp))
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = from,
            onValueChange = onFromChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(28.dp)),
            trailingIcon = {
                IconButton(onClick = {
                    showSuggestionsFrom.value = !showSuggestionsFrom.value
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search for Destinations",
                        tint = Color.Gray
                    )
                }
            },
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = { Text(stringResource(id = R.string.enter_the_starting_point), fontSize = 20.sp) },
            singleLine = true,
            shape = RoundedCornerShape(28.dp),
        )

        if (showSuggestionsFrom.value) {
            PopularDestinationsList(
                destinations = popularFrom,
                onDestinationSelected = {
                    onFromChange(it)
                    onEnterLink(findLocationLink(it))
                    onEnterFloor(findLocationFloor(it))
                    showSuggestionsFrom.value = false
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = to,
            onValueChange = onToChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(28.dp)),
            trailingIcon = {
                IconButton(onClick = {
                    showSuggestionsTo.value = !showSuggestionsTo.value
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search for Destinations",
                        tint = Color.Gray
                    )
                }
            },
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = { Text(stringResource(id = R.string.enter_the_ending_point), fontSize = 20.sp) },
            singleLine = true,
            shape = RoundedCornerShape(28.dp),
        )

        if (showSuggestionsTo.value) {
            PopularDestinationsList(
                destinations = popularTo,
                onDestinationSelected = {
                    onToChange(it)
                    onEnterLink(findLocationLink(it))
                    onEnterFloor(findLocationFloor(it))
                    showSuggestionsTo.value = false
                }
            )
        }

        when {
            to.isEmpty() && from.isEmpty() -> {

            }
            to.isNotEmpty() && from.isNotEmpty() -> {
                onEnterLink(find2Locations(from, to))
                onEnterFloor(findLocationFloor(to))
            }
            to.isEmpty() && from.isNotEmpty() -> {
                onEnterLink(findLocationLink(from))
                onEnterFloor(findLocationFloor(from))
            }
            else -> {
                onEnterLink(findLocationLink(to))
                onEnterFloor(findLocationFloor(to))
            }
        }


        Button(
            onClick = {
                onButtonClick(from, to)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(4.dp)
                .shadow(3.dp, shape = CircleShape),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.Black
            ), // Используем ColorButton1
            shape = RoundedCornerShape(28.dp),
        ) {
            Text(stringResource(id = R.string.build_a_route_button), color = MaterialTheme.colorScheme.onPrimary, fontSize = 20.sp)
        }
    }
}

@Composable
fun TopSection2(onButtonClick: () -> Unit) { //Окошко отмены маршрута
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(18.dp))
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
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
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(28.dp),
        ) {
            Text(stringResource(id = R.string.new_route_button), color = MaterialTheme.colorScheme.onPrimary, fontSize = 20.sp)
        }
    }
}

@Composable
fun CenterSection() {
}

@Composable
fun RouteToast(uiState: LocationState) {
    val context = LocalContext.current

    // Показываем Toast, когда есть что показать и не идёт загрузка
    LaunchedEffect(uiState.routeTimeMinutes, uiState.isRouteLoading) {
        when {
            uiState.isRouteLoading -> {
                Toast.makeText(context, "Поиск маршрута...", Toast.LENGTH_SHORT).show()
            }
            uiState.routeTimeMinutes != null && uiState.routeTimeMinutes != -1 -> {
                Toast.makeText(
                    context,
                    "Пройдите вдоль коридора — ${uiState.routeTimeMinutes} мин.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}


@Composable
fun RouteBar() { //Окошко с временем маршрута
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(18.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Пройдите вдоль коридора — 2 минуты",
            modifier = Modifier.fillMaxWidth(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable // навигация по этажам
fun FloorsColumn(
    clickedFloor: Int?,
    needFloor1: Int?,
    needFloor2: Int?,
    onFloorClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .wrapContentSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        val floors = listOf(5, 4, 3, 2, 1, 0)

        floors.forEach { floor ->
            val isSelected = clickedFloor == floor
            val isneed1 = needFloor1 == floor
            val isneed2 = needFloor2 == floor

            Button(
                onClick = { onFloorClick(floor) },
                shape = RectangleShape,
                enabled = !isSelected,
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        isSelected -> MaterialTheme.colorScheme.tertiaryContainer
                        isneed1 -> MaterialTheme.colorScheme.primaryContainer
                        isneed2 -> MaterialTheme.colorScheme.primaryContainer
                        else -> MaterialTheme.colorScheme.primary
                    },
                    contentColor = if (isSelected)  MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("$floor")
            }
        }
    }
}
