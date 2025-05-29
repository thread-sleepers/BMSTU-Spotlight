package com.example.bmstu_spotlight.location_screen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.location_screen.data.PopularDestinations

@Composable
fun PopularDestinationsList(
    destinations: List<PopularDestinations>,
    onDestinationSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                1.dp,
                Color.LightGray,
                RoundedCornerShape(8.dp)
            )
    ) {
        destinations.forEach { destination ->
            Text(
                text = destination.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onDestinationSelected(destination.name)
                    }
                    .padding(12.dp)
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
                color = MaterialTheme.colorScheme.inverseSurface,
                fontSize = 16.sp,
            )
            Divider(
                color = Color.LightGray,
                thickness = 0.5.dp
            )
        }
    }
}

