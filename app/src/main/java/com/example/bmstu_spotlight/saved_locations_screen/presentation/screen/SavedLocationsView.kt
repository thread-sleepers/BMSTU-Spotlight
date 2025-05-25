package com.example.bmstu_spotlight.saved_locations_screen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bmstu_spotlight.R
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
import com.example.bmstu_spotlight.saved_locations_screen.data.repository.LocationDetails
import com.example.bmstu_spotlight.saved_locations_screen.presentation.components.SectionHeader
import com.example.bmstu_spotlight.saved_locations_screen.presentation.screen.components.LocationList

@Composable
fun SavedLocationsView(
    recentState: List<LocationDetails>,
    favoritesState: List<LocationDetails>,
    onLinkClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp)
    ) {
        CustomTopBar(stringResource(R.string.locations))

        SectionHeader(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.recents)
        )
        LocationList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
            items = recentState,
            icon = Icons.Default.FavoriteBorder,
            iconContentDescription = "Recent Location",
            onReferenceClick = onLinkClick
        )

        SectionHeader(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.saved)
        )
        LocationList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
                .background(MaterialTheme.colorScheme.surfaceVariant),
            items = favoritesState,
            icon = Icons.Default.Favorite,
            iconContentDescription = "Saved Location",
            onReferenceClick = onLinkClick
        )
    }
}
