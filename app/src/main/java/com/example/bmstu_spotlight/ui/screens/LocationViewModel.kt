package com.example.bmstu_spotlight.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity
import com.example.bmstu_spotlight.DataHolder

class LocationViewModel : ViewModel() {

    // LiveData для хранения выбранного узла (например, лаборатория, аудитория и т. д.).
    // _selectedNode — это изменяемая версия, а selectedNode — публичная LiveData для наблюдения.
    private val _selectedNode = MutableLiveData<NodeEntity?>()
    val selectedNode: LiveData<NodeEntity?> = _selectedNode

    // LiveData для управления состоянием шторки (открыта/закрыта).
    // По умолчанию шторка закрыта (false).
    private val _showSheet = MutableLiveData<Boolean>(false)
    val showSheet: LiveData<Boolean> = _showSheet

    // LiveData для хранения текущего масштаба карты.
    // По умолчанию масштаб установлен на 1x.
    private val _scale = MutableLiveData<Float>(1f)
    val scale: LiveData<Float> = _scale

    // LiveData для хранения положения карты по оси X.
    private val _offsetX = MutableLiveData<Float>(0f)
    val offsetX: LiveData<Float> = _offsetX

    // LiveData для хранения положения карты по оси Y.
    private val _offsetY = MutableLiveData<Float>(0f)
    val offsetY: LiveData<Float> = _offsetY

    /**
     * Метод устанавливает выбранный узел и открывает шторку с его информацией.
     * Вызывается при выборе узла пользователем.
     *
     * @param nodeId - ID узла, который выбрал пользователь.
     */
    fun selectNode(nodeId: String) {
        _selectedNode.value = DataHolder.nodes.find { it.nodeId.toString() == nodeId }
        _showSheet.value = _selectedNode.value != null
    }


    /**
     * Метод закрывает шторку и сбрасывает выбранный узел.
     * Вызывается при нажатии на кнопку закрытия.
     */
    fun closeSheet() {
        _showSheet.value = false // Закрываем шторку.
        _selectedNode.value = null // Очищаем выбранный узел.
        DataHolder.selectedNodeId = null
    }

    /**
     * Масштабируем карту, ограничивая минимальный (1x) и максимальный (3x) уровни.
     * @param newScale - новый масштаб карты.
     */
    fun updateScale(newScale: Float) {
        _scale.value = newScale.coerceIn(1f, 3f) // Ограничиваем значение от 1x до 3x.
    }

    /**
     * Метод обновляет положение карты, изменяя смещение по X и Y.
     * @param newX - новое значение смещения по X.
     * @param newY - новое значение смещения по Y.
     */
    fun updateOffset(newX: Float, newY: Float) {
        _offsetX.value = newX
        _offsetY.value = newY
    }
}

