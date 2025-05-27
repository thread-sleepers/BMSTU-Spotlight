package com.example.bmstu_spotlight.data.utils

import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType

fun getNodeTypeByReference(reference: String): NodeType {
    return when {
        reference.contains("stairs", ignoreCase = true) -> NodeType.STAIRS
        reference.equals("wc", ignoreCase = true) -> NodeType.RESTROOM
        reference.matches(Regex("""\d+(\.\d+)?""")) -> NodeType.CLASSROOM
        else -> NodeType.HALL
    }
}