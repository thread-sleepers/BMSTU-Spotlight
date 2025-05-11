package com.example.bmstu_spotlight.ui.helper_functions

import com.example.bmstu_spotlight.DataHolder

fun findLocationLink(name: String): String? {
    var locationLink: String? = null
    for (item in DataHolder.nodes) {
        if (item.nodeName == name) {
            locationLink = item.reference
        }
    }
    return locationLink
}