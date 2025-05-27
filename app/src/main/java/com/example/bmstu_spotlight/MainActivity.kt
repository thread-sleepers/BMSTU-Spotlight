package com.example.bmstu_spotlight

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bmstu_spotlight.auth_screen.presentation.screen.AuthScreen
import com.example.bmstu_spotlight.ui.screens.BottomBarScreen
import com.example.bmstu_spotlight.ui.screens.RootNavGraph
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightAppNewTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMSTUSpotlightAppNewTheme {
                RootNavGraph()
            }
        }
    }
}


