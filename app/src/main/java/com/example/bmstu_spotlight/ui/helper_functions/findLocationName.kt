package com.example.bmstu_spotlight.ui.helper_functions
import com.example.bmstu_spotlight.DataHolder

fun findLocationName(link: String): String? {
    var locationName: String? = null
    for (item in DataHolder.nodes) {
        if (item.reference == link) {
            locationName = item.nodeName
        }
    }
    return locationName
}