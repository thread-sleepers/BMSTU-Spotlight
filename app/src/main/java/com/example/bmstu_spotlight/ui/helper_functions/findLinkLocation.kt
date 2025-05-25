package com.example.bmstu_spotlight.ui.helper_functions
import com.example.bmstu_spotlight.DataHolder

fun findLinkLocation(link: String): String? {
    var locationName: String? = null
    var link2: String? = null
    for (item in DataHolder.nodes) {
        link2 = item.reference.substringBefore("#")
        if (link2 == link) {
            locationName = item.nodeName
            break
        }
    }
    return locationName
}