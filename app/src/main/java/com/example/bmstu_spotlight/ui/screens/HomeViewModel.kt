package com.example.bmstu_spotlight.ui.screens

import androidx.lifecycle.ViewModel
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType
import com.example.bmstu_spotlight.DataHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.bmstu_spotlight.route.domain.models.Node
import com.example.bmstu_spotlight.domain.mappers.toDomain


data class HomeState(
    val selectedNodeType: NodeType? = null,
    val filteredNodes: List<Node> = emptyList()
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    fun selectNodeType(nodeType: NodeType) {
        _uiState.update { state ->
            val filteredNodes = DataHolder.nodes.map { it.toDomain() }.filter { it.type == nodeType }
            state.copy(selectedNodeType = nodeType, filteredNodes = filteredNodes)
        }
    }

    fun clearSelection() {
        _uiState.update { HomeState() }
    }
}

