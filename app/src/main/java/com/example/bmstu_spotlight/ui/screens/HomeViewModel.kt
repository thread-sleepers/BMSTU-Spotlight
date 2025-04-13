package com.example.bmstu_spotlight.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType
import com.example.bmstu_spotlight.DataHolder

class HomeViewModel : ViewModel() {
    // LiveData для хранения выбранного типа узла (например, CLASSROOM, CANTEEN и т. д.)
    private val _selectedNodeType = MutableLiveData<NodeType?>()
    val selectedNodeType: LiveData<NodeType?> = _selectedNodeType

    // LiveData для хранения списка отфильтрованных узлов
    private val _filteredNodes = MutableLiveData<List<NodeEntity>>()
    val filteredNodes: LiveData<List<NodeEntity>> = _filteredNodes

    /**
     * Метод устанавливает выбранный тип узла и фильтрует список узлов по этому типу.
     * Вызывается, когда пользователь выбирает категорию (например, "Кафетерии").
     *
     * @param nodeType - тип узла, который выбрал пользователь
     */
    fun selectNodeType(nodeType: NodeType) {
        _selectedNodeType.value = nodeType // Сохраняем выбранный тип узла
        filterNodesByType(nodeType) // Фильтруем список узлов по выбранному типу
    }

    /**
     * Метод сбрасывает выбор категории и очищает список узлов.
     * Вызывается, когда пользователь возвращается в главное меню.
     */
    fun clearSelection() {
        _selectedNodeType.value = null // Сбрасываем выбор узла
        _filteredNodes.value = emptyList() // Очищаем список узлов
    }

    /**
     * Фильтрует список узлов в DataHolder по выбранному типу узла.
     * Этот метод вызывается внутри `selectNodeType()`.
     *
     * @param nodeType - тип узла, по которому выполняется фильтрация
     */
    private fun filterNodesByType(nodeType: NodeType) {
        _filteredNodes.value = DataHolder.nodes.filter { it.nodeType == nodeType } // Фильтруем узлы
    }
}


