package com.example.bmstu_spotlight.ui.helper_functions
import com.example.bmstu_spotlight.DataHolder

fun findLocationFloor(name: String): Int {
    var locationFloor: Int = 3

    for (item in DataHolder.nodes) {
        if (item.nodeName == name) {
            locationFloor = item.floor
        }
    }
    return locationFloor

}