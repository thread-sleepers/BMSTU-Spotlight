package com.example.bmstu_spotlight.ui.helper_functions

import com.example.bmstu_spotlight.DataHolder

fun find2Locations(from: String, to: String): String? {
    var coupledLink: String? = null
    for (item in DataHolder.routes) {
        if (item.from == from && item.to == to) {
            coupledLink = item.emptyRouteLink
        }
    }
    return coupledLink
}