package com.example.bmstu_spotlight.ui.helper_functions

import com.example.bmstu_spotlight.DataHolder

fun findRoute(to: String, from: String, selectedFloor: Int): String? {
    var routeLink: String? = null
    for (item in DataHolder.routes) {
        if ((item.from == from && item.to == to && item.floor == selectedFloor) || (item.from == to && item.to == from && item.floor == selectedFloor)) {
            routeLink = item.routeLink
        }
    }
    return routeLink
}