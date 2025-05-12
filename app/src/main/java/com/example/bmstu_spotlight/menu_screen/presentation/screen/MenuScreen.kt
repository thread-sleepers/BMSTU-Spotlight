package com.example.bmstu_spotlight.menu_screen.presentation.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.DataHolder
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bmstu_spotlight.R
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import com.example.bmstu_spotlight.menu_screen.presentation.view_model.MenuViewModel.*
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType
import com.example.bmstu_spotlight.menu_screen.domain.models.Node
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
import com.example.bmstu_spotlight.ui.screens.BottomBarScreen
import com.example.bmstu_spotlight.menu_screen.presentation.view_model.MenuViewModel

@Composable
fun MenuScreen(navController: NavHostController, viewModel: MenuViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        CustomTopBar(stringResource(R.string.app_name))

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
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {  },
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type1_button),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        item {
            Box(
                Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .clickable { onItemClick(NodeType.LABORATORY) },
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type2_button),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        item {
            Box(
                Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type3_button),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        item {
            Box(
                Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { onItemClick(NodeType.CLASSROOM) },
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type4_button),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        item {
            Box(
                Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type5_button),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        item {
            Box(
                Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type6_button),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

        }

        item {
            Box(
                Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { onItemClick(NodeType.CANTEEN) },
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type7_button),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        item {
            Box(
                Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .clickable( onClick = {  }),
                contentAlignment = Alignment.Center
            )
            {
                Text(stringResource(id = R.string.type8_button),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
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
                        .clip(shape = RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .height(80.dp)
                        //.background(ColorBack2)
                        .clickable {
                            DataHolder.selectedNodeId = node.id
                            navController.navigate(BottomBarScreen.Location.route)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(node.name, fontSize = 24.sp, textAlign = TextAlign.Center,
                        //color = ColorText1
                    )
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
            //colors = ButtonDefaults.buttonColors(containerColor = ColorButton1, contentColor = Color.Black),
            shape = RoundedCornerShape(28.dp),
        ) {
            Text(stringResource(id = R.string.back_button), fontSize = 20.sp//, color = ColorText2
            )
        }
    }
}