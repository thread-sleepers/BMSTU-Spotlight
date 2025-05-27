package com.example.bmstu_spotlight.saved_locations_screen.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmstu_spotlight.saved_locations_screen.data.repository.LocationDetails

@Composable
fun LocationList(
    items: List<LocationDetails>,
    icon: ImageVector,
    iconContentDescription: String,
    modifier: Modifier,
    onReferenceClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier.background(MaterialTheme.colorScheme.surfaceVariant)) {
        items(items) { it ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.clickable{
                    onReferenceClick(it.locationName)
                }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = iconContentDescription,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = it.locationName,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Walking time",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = it.time,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}