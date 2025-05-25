package com.example.bmstu_spotlight.ui.screens

sealed class RootScreen(val route: String) {
    object Auth: RootScreen("auth")
    object MainApp: RootScreen("main_app")
}