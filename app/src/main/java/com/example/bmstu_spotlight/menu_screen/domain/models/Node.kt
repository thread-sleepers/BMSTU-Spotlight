package com.example.bmstu_spotlight.menu_screen.domain.models

import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType
import java.util.UUID

data class Node(
    val id: UUID,
    val name: String,
    val description: String,
    val type: NodeType,
    val xCoordinate: Float,
    val yCoordinate: Float,
    val roomNumber: String?,
    val isAvailable: Boolean
)