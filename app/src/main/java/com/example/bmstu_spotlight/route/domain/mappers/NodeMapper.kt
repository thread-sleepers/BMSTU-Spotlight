package com.example.bmstu_spotlight.domain.mappers

import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity
import com.example.bmstu_spotlight.route.domain.models.Node

fun NodeEntity.toDomain(): Node {
    return Node(
        id = this.nodeId,
        name = this.nodeName,
        description = this.nodeDescription,
        type = this.nodeType,
        xCoordinate = this.xCoordinate,
        yCoordinate = this.yCoordinate,
        roomNumber = this.roomNumber,
        isAvailable = this.isAvailable
    )
}

