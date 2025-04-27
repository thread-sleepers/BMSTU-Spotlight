package com.example.bmstu_spotlight.location_screen.presentation.view_model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.ViewModel
import com.example.bmstu_spotlight.menu_screen.domain.models.Node
import com.example.bmstu_spotlight.domain.mappers.toDomain
import com.example.bmstu_spotlight.DataHolder
import java.util.UUID

data class LocationState(
    val selectedNode: Node? = null,
    val showSheet: Boolean = false,
    val scale: Float = 1f,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val showNewTopSection: Boolean = DataHolder.showNewTopSection
)

class LocationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LocationState())
    val uiState = _uiState.asStateFlow()

    fun selectNode(nodeId: UUID) {
        val node = DataHolder.nodes.find { it.nodeId == nodeId }?.toDomain()
        _uiState.update { it.copy(selectedNode = node, showSheet = node != null) }
    }

    fun closeSheet() {
        DataHolder.selectedNodeId = null // Обнуляем выбранный узел
        _uiState.update { LocationState() } // Сбрасываем состояние экрана
    }

    fun updateScale(newScale: Float) {
        _uiState.update { it.copy(scale = newScale.coerceIn(1f, 3f)) }
    }

    fun updateOffset(newX: Float, newY: Float) {
        _uiState.update { it.copy(offsetX = newX, offsetY = newY) }
    }

    fun toggleTopSection(visible: Boolean) {
        _uiState.update { it.copy(showNewTopSection = visible) }
        DataHolder.showNewTopSection = visible
    }
}

