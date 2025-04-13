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
<<<<<<<< HEAD:app/src/main/java/com/example/bmstu_spotlight/ui/screens/saved_locations_screen/presentation/screen/SavedLocationsScreen.kt
import com.example.bmstu_spotlight.ui.screens.saved_locations_screen.presentation.components.LocationList
import com.example.bmstu_spotlight.ui.screens.saved_locations_screen.presentation.components.SectionHeader
import com.example.bmstu_spotlight.ui.screens.saved_locations_screen.presentation.view_model.LocationViewModel
========
import com.example.bmstu_spotlight.saved_locations_screen.presentation.components.LocationList
import com.example.bmstu_spotlight.saved_locations_screen.presentation.components.SectionHeader
import com.example.bmstu_spotlight.saved_locations_screen.presentation.view_model.LocationViewModel
import dagger.hilt.android.components.ViewModelComponent
>>>>>>>> 8cb0024 (minor changes to db):app/src/main/java/com/example/bmstu_spotlight/saved_locations_screen/presentation/screen/SavedLocationsScreen.kt

class SavedLocationsScreen {
    @Preview(showSystemUi = true)
    @Composable
    fun SavedLocationsScreen(viewModel: LocationViewModel = hiltViewModel) {
        val recent by viewModel.recentLocations.collectAsState()
        val favorites by viewModel.favoriteLocations.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
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
                    modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
                )
            }

            SectionHeader(title = "Recent", paddingStart = 32.dp)
            LocationList(
                items = recent,
                icon = Icons.Default.FavoriteBorder,
                iconContentDescription = "Recent Location",
                itemPaddingStart = 32.dp
            )

            SectionHeader(title = "Saved", paddingStart = 32.dp)
            LocationList(
                items = favorites,
                icon = Icons.Default.Favorite,
                iconContentDescription = "Saved Location",
                itemPaddingStart = 32.dp
            )
        }
    }

<<<<<<<< HEAD:app/src/main/java/com/example/bmstu_spotlight/ui/screens/saved_locations_screen/presentation/screen/SavedLocationsScreen.kt
}
========

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
>>>>>>>> 8cb0024 (minor changes to db):app/src/main/java/com/example/bmstu_spotlight/saved_locations_screen/presentation/screen/SavedLocationsScreen.kt
