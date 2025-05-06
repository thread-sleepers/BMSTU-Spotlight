package com.example.bmstu_spotlight.ui.handlers
import com.example.bmstu_spotlight.DataHolder
import com.example.bmstu_spotlight.saved_locations_screen.data.repository.FakeLocationRepository
import com.example.bmstu_spotlight.ui.screens.BottomBarScreen
import com.example.bmstu_spotlight.ui.screens.BottomBarScreen.SavedLocationsScreen

fun findLocationName(link: String): String? {
    var locationName: String? = null
    for (item in DataHolder.nodes) {
        if (item.reference == link) {
            locationName = item.nodeName
        }
    }
    return locationName
}