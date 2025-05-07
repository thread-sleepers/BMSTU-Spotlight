package com.example.bmstu_spotlight.location_screen.presentation.screen

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
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
import com.example.bmstu_spotlight.ui.theme.ColorBack1
import com.example.bmstu_spotlight.ui.theme.ColorBack2
import com.example.bmstu_spotlight.ui.theme.ColorButton1
import com.example.bmstu_spotlight.ui.theme.ColorButton2
import com.example.bmstu_spotlight.ui.theme.ColorInput1
import com.example.bmstu_spotlight.ui.theme.ColorText2
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.*
import com.example.bmstu_spotlight.location_screen.data.popularFrom
import com.example.bmstu_spotlight.location_screen.data.popularTo
import com.example.bmstu_spotlight.location_screen.presentation.view_model.LocationViewModel
import com.example.bmstu_spotlight.ui.helper_functions.findLocationLink
import com.example.bmstu_spotlight.ui.helper_functions.findLocationName
import com.example.bmstu_spotlight.ui.helper_functions.findRoute
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
import com.example.bmstu_spotlight.ui.helper_functions.find2Locations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(viewModel: LocationViewModel = viewModel(), mapLink: String?) {
    val uiState by viewModel.uiState.collectAsState()

    val messageLocation1 = remember { mutableStateOf("") }
    val messageLocation2 = remember { mutableStateOf("") }

    val currentMapLink = remember { mutableStateOf(mapLink ?: uiState.defaultLink) }
    val onEnterLink: (String?) -> Unit = {
            link -> currentMapLink.value = link ?: uiState.defaultLink
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
                    loadUrl(currentMapLink.value)

                    if (currentMapLink.value != uiState.defaultLink) {
                        messageLocation2.value = findLocationName(currentMapLink.value).toString()
                    }
                }
            },
            update = {
                it.loadUrl(currentMapLink.value)
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
                TopSection2(onButtonClick = { viewModel.toggleTopSection(false) })
                RouteBar()
                //}
            } else { // Когда маршрут ещё не начат
                TopSection1(
                    from = messageLocation1,
                    to = messageLocation2,
                    onButtonClick = { loc1, loc2 ->
                        DataHolder.location1 = loc1 // Сохранение данных в DataHolder
                        DataHolder.location2 = loc2
                        viewModel.toggleTopSection(true)
                        onEnterLink(findRoute(loc1, loc2))
                    },
                    onEnterLink = onEnterLink
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        DataHolder.selectedNodeId?.let { nodeId ->
            viewModel.selectNode(nodeId)
        }
    }

    if (uiState.showSheet) {
        uiState.selectedNode?.let { node ->
            messageLocation2.value = node.name

            ModalBottomSheet(
                onDismissRequest = { viewModel.closeSheet() },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = ColorBack1,
                scrimColor = Color.Transparent
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
                            colors = ButtonDefaults.buttonColors(containerColor = ColorButton1),
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
    from: MutableState<String>,
    to: MutableState<String>,
    onButtonClick: (String, String) -> Unit,
    onEnterLink: (String?) -> Unit
) { //Окошко ввода начальной и конечной локации
    val showSuggestionsFrom = remember { mutableStateOf(false) }
    val showSuggestionsTo = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp))
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = from.value,
            onValueChange = { newText ->
               from.value = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(ColorInput1, shape = RoundedCornerShape(28.dp)),
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
                    from.value = it
                    onEnterLink(findLocationLink(it))
                    showSuggestionsFrom.value = false
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = to.value,
            onValueChange = { newText ->
                to.value = newText
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
                    to.value = it
                    onEnterLink(findLocationLink(it))
                    showSuggestionsTo.value = false
                }
            )
        }

        if (to.value != "" && from.value != "") {
            onEnterLink(find2Locations(from.value, to.value))
        }


        Button(
            onClick = {
                onButtonClick(from.value, to.value)
            },
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
            Text(stringResource(id = R.string.build_a_route_button), color = ColorText2, fontSize = 20.sp)
        }
    }
}

@Composable
fun TopSection2(onButtonClick: () -> Unit) { //Окошко отмены маршрута
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp))
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
                containerColor = ColorButton2,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(28.dp),
        ) {
            Text(stringResource(id = R.string.new_route_button), color = ColorText2, fontSize = 20.sp)
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
