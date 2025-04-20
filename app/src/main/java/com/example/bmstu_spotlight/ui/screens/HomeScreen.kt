package com.example.bmstu_spotlight.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.bmstu_spotlight.DataHolder
import com.example.bmstu_spotlight.ui.theme.ColorBack1
import com.example.bmstu_spotlight.ui.theme.ColorBack2
import com.example.bmstu_spotlight.ui.theme.ColorBack3
import com.example.bmstu_spotlight.ui.theme.ColorButton1
import com.example.bmstu_spotlight.ui.theme.ColorInput1
import com.example.bmstu_spotlight.ui.theme.ColorText1
import com.example.bmstu_spotlight.ui.theme.ColorText2
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bmstu_spotlight.R
import androidx.compose.runtime.*
import com.example.bmstu_spotlight.ui.screens.HomeViewModel.*
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType
import com.example.bmstu_spotlight.route.domain.models.Node

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        TopSection3()

        if (uiState.selectedNodeType == null) {
            MenuSection { nodeType -> viewModel.selectNodeType(nodeType) }
        } else {
            SecondMenuSection(
                nodes = uiState.filteredNodes,
                onBackClick = { viewModel.clearSelection() },
                navController = navController
            )
        }
    }
}

// Герб мгту
@Composable
fun Emblem(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.emblem),
            contentDescription = "Логотип",
            modifier = Modifier.size(40.dp)
        )
    }
}


@Composable
fun TopSection3() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorBack1, shape = RoundedCornerShape(18.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Emblem()

            Text(
                stringResource(id = R.string.app_name),
                color = ColorText2,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )

            Emblem()

        }
    }
}



@Composable
fun MenuSection(onItemClick: (NodeType) -> Unit) { // Отображение кнопок из меню

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxHeight(0.92f),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(ColorBack2)
                    .clickable {  },
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type1_button),
                    color = ColorText1,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(ColorBack3)
                    .clickable { onItemClick(NodeType.LABORATORY) },
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type2_button),
                    color = ColorText2,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(ColorBack3)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type3_button),
                    color = ColorText2,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(ColorBack2)
                    .clickable { onItemClick(NodeType.CLASSROOM) },
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type4_button),
                    color = ColorText1,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(ColorBack2)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type5_button),
                    color = ColorText1,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(ColorBack3)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type6_button),
                    color = ColorText2,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(ColorBack3)
                    .clickable { onItemClick(NodeType.CANTEEN) },
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type7_button),
                    color = ColorText2,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(ColorBack2)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type8_button),
                    color = ColorText1,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

    }
}

@Composable
fun SecondMenuSection(
    nodes: List<Node>,
    onBackClick: () -> Unit,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.96f)
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.90f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(nodes) { node ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(ColorBack2)
                        .clickable {
                            DataHolder.selectedNodeId = node.id
                            navController.navigate(BottomBarScreen.Location.route)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(node.name, fontSize = 24.sp, textAlign = TextAlign.Center, color = ColorText1)
                }
            }
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(8.dp)
                .shadow(3.dp, shape = CircleShape),
            colors = ButtonDefaults.buttonColors(containerColor = ColorButton1, contentColor = Color.Black),
            shape = RoundedCornerShape(28.dp),
        ) {
            Text(stringResource(id = R.string.back_button), fontSize = 20.sp, color = ColorText2)
        }
    }
}







