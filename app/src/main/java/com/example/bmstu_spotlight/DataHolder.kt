package com.example.bmstu_spotlight

import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType
import java.util.UUID

object DataHolder {
    var location1: String = ""
    var location2: String = ""
    var showNewTopSection: Boolean = false

    var search1: String = ""

// Все ниже комментария удалим, когда появятся карты
    var markerPositions = listOf(
        Pair(100f, 200f), // Маркер 1
        Pair(400f, 300f), // Маркер 2
        Pair(250f, 550f), // Маркер 3
        Pair(600f, 750f)  // Маркер 4
    )
    var targetMarkerIndex: Int? = null // Индекс маркера, на который нужно переместиться
    var targetScale: Float = 2f // Степень увеличения при фокусировке на маркере

    // Переменная для хранения выбранного узла
    var selectedNodeId: UUID? = null

    val nodes = listOf(
        // Аудитории (10 элементов)
        NodeEntity(UUID.randomUUID(), "Аудитория 101", "Лекционная аудитория", 150f, 300f, NodeType.CLASSROOM, "101", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 102", "Аудитория для групповых занятий", 160f, 310f, NodeType.CLASSROOM, "102", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 103", "Компьютерный класс", 170f, 320f, NodeType.CLASSROOM, "103", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 104", "Аудитория с проектором", 180f, 330f, NodeType.CLASSROOM, "104", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 105", "Помещение для семинаров", 190f, 340f, NodeType.CLASSROOM, "105", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 106", "Аудитория для конференций", 200f, 350f, NodeType.CLASSROOM, "106", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 107", "Класс с интерактивной доской", 210f, 360f, NodeType.CLASSROOM, "107", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 108", "Класс для лабораторных работ", 220f, 370f, NodeType.CLASSROOM, "108", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 109", "Помещение для практических занятий", 230f, 380f, NodeType.CLASSROOM, "109", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Аудитория 110", "Большой лекционный зал", 240f, 390f, NodeType.CLASSROOM, "110", true, UUID.randomUUID(), "lol"),

        // Столовые (2 элемента)
        NodeEntity(UUID.randomUUID(), "Кафетерий", "Зона питания для студентов", 550f, 900f, NodeType.CANTEEN, "C1", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Ресторан в кампусе", "Меню для преподавателей и гостей", 570f, 920f, NodeType.CANTEEN, "C2", true, UUID.randomUUID(), "lol"),

        // Лаборатории (2 элемента)
        NodeEntity(UUID.randomUUID(), "Лаборатория химии", "Оборудована для химических экспериментов ffffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffffffffffff ffffffffffffffffffffffffffffffff ffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffffff", 750f, 1100f, NodeType.LABORATORY, "L1", true, UUID.randomUUID(), "lol"),
        NodeEntity(UUID.randomUUID(), "Физическая лаборатория", "Анализ физических процессов", 770f, 1120f, NodeType.LABORATORY, "L2", true, UUID.randomUUID(), "lol")
    )

}
