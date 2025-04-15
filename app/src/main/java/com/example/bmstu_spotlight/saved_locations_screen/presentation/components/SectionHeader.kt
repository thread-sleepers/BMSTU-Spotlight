package com.example.bmstu_spotlight.saved_locations_screen.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SectionHeader(modifier: Modifier, title: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                start = 32.dp,
                top = 24.dp,
                bottom = 8.dp
            )
        )
    }
}


