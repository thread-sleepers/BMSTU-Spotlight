package com.example.bmstu_spotlight.saved_locations_screen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.saved_locations_screen.presentation.components.LocationList
import com.example.bmstu_spotlight.saved_locations_screen.presentation.components.SectionHeader
import com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model.LocationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedLocationsScreen(
    viewModel: LocationViewModel = koinViewModel()
) {
    val recent by viewModel.recentLocations.collectAsState()
    val favorites by viewModel.favoriteLocations.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        // Title Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Destinations",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 40.dp, bottom = 16.dp)
            )
        }

        // Recent Section
        SectionHeader(title = "Recent", paddingStart = 32.dp)
        LocationList(
            items = viewModel.recentLocations.value,
            icon = Icons.Default.FavoriteBorder,
            iconContentDescription = "Recent Location",
            itemPaddingStart = 32.dp
        )

        // Saved Section
        SectionHeader(title = "Saved", paddingStart = 32.dp)
        LocationList(
            items = viewModel.favoriteLocations.value,
            icon = Icons.Default.Favorite,
            iconContentDescription = "Saved Location",
            itemPaddingStart = 32.dp
        )
    }
}


//    @Composable
//    fun SavedLocationsScreen(val viewModel: LocationViewModel = hiltViewModel) {
//        val recent by viewModel.recentLocations.collectAsState()
//        val favorites by viewModel.favoriteLocations.collectAsState()
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.LightGray)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.White),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "Destinations",
//                    fontSize = 28.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
//                )
//            }
//
//            SectionHeader(title = "Recent", paddingStart = 32.dp)
//            LocationList(
//                items = recent,
//                icon = Icons.Default.FavoriteBorder,
//                iconContentDescription = "Recent Location",
//                itemPaddingStart = 32.dp
//            )
//
//            SectionHeader(title = "Saved", paddingStart = 32.dp)
//            LocationList(
//                items = favorites,
//                icon = Icons.Default.Favorite,
//                iconContentDescription = "Saved Location",
//                itemPaddingStart = 32.dp
//            )
//        }
//    }
//
