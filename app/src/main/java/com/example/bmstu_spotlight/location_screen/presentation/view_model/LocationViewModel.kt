package com.example.bmstu_spotlight.location_screen.presentation.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmstu_spotlight.BuildConfig
import com.example.bmstu_spotlight.menu_screen.domain.models.Node
import com.example.bmstu_spotlight.domain.mappers.toDomain
import com.example.bmstu_spotlight.DataHolder
import com.example.bmstu_spotlight.route.domain.usecases.FindShortestPathUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class LocationState(
    val selectedNode: Node? = null,
    val showSheet: Boolean = false,
    val scale: Float = 1f,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val showNewTopSection: Boolean = DataHolder.showNewTopSection,
    val defaultLink2: String = "https://api.maptiler.com/maps/019711d7-efab-77f0-ac8d-eda6e16f7e21/?key=MyhK9LG5Sf1nMCLXhT8v#18/55.76643/37.68636",
    val defaultLink3: String = "https://api.maptiler.com/maps/01969592-b55a-7cf6-a450-cda9af40bac7/?key=${BuildConfig.API_KEY_3}#18.31/55.766431/37.685916",
    val defaultLink4: String = "https://api.maptiler.com/maps/0196bb8a-6a2d-70a0-babe-6b793c074544/?key=pEC9gVZBA06hIDiYD3bk#18/55.76643/37.68636",
    val defaultFloor: Int = 3,
    var messageLocation1: String = "",
    var messageLocation2: String = "",
    var currentMapLink: String = defaultLink3,
    var currentFloor: Int = defaultFloor,
    var needFloor1: Int = 6,
    var needFloor2: Int = 6,
    val routePath: List<String> = emptyList(),
    val routeTimeMinutes: Int? = null,
    val isRouteLoading: Boolean = false
)

class LocationViewModel(
    private val findShortestPathUseCase: FindShortestPathUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(LocationState())
    val uiState = _uiState.asStateFlow()

    fun updateMessageLocation1(value: String) {
        _uiState.update { it.copy(messageLocation1 = value) }
    }

    fun updateMessageLocation2(value: String) {
        _uiState.update { it.copy(messageLocation2 = value) }
    }

    fun updateMapLink(link: String?, floor: Int?) {
        _uiState.update { state ->
            val newLink = when(floor) {
                2 -> link ?: state.defaultLink2
                3 -> link ?: state.defaultLink3
                4 -> link ?: state.defaultLink4
                else -> link ?: state.defaultLink4}
            state.copy(currentMapLink = newLink)
        }
    }

    fun updateFloor(floor: Int?){
        _uiState.update { state ->
            state.copy(currentFloor = floor ?: 0)
        }
    }

    fun updaten1Floor(floor: Int?){
        _uiState.update { state ->
            state.copy(needFloor1 = floor ?: 0)
        }
    }

    fun updaten2Floor(floor: Int?){
        _uiState.update { state ->
            state.copy(needFloor2 = floor ?: 0)
        }
    }

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

    fun findRoute(from: String, to: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isRouteLoading = true) }

            try {
                val result = findShortestPathUseCase.execute(from, to)
                _uiState.update {
                    it.copy(
                        routePath = result.path,
                        routeTimeMinutes = result.time.toInt(),
                        isRouteLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(routePath = emptyList(), routeTimeMinutes = null, isRouteLoading = false)
                }
            }
        }
    }

}

