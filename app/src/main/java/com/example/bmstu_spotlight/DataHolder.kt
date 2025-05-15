package com.example.bmstu_spotlight

import com.example.bmstu_spotlight.data.datasource.local.entities.NodeEntity
import com.example.bmstu_spotlight.data.datasource.local.entities.NodeType
import com.example.bmstu_spotlight.location_screen.data.PopularDestinations
import java.util.UUID

object DataHolder {
    var location1: String = ""
    var location2: String = ""
    var floor1: Int = 3
    var link: String = ""
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
        NodeEntity(UUID.randomUUID(), "Аудитория 384", "Лекционная аудитория", 150f, 300f, NodeType.CLASSROOM, "384", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 385", "Аудитория для групповых занятий", 160f, 310f, NodeType.CLASSROOM, "385", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 386", "Компьютерный класс", 170f, 320f, NodeType.CLASSROOM, "386", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 387", "Аудитория с проектором", 180f, 330f, NodeType.CLASSROOM, "387", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 388", "Помещение для семинаров", 190f, 340f, NodeType.CLASSROOM, "388", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 389", "Аудитория для конференций", 200f, 350f, NodeType.CLASSROOM, "389", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 390", "Класс с интерактивной доской", 210f, 360f, NodeType.CLASSROOM, "390", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 391", "Класс для лабораторных работ", 220f, 370f, NodeType.CLASSROOM, "391", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 392", "Помещение для практических занятий", 230f, 380f, NodeType.CLASSROOM, "392", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 393", "Большой лекционный зал", 240f, 390f, NodeType.CLASSROOM, "393", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 394", "Не менее большой лекционный зал", 250f, 400f, NodeType.CLASSROOM, "394", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635",3),
        NodeEntity(UUID.randomUUID(), "Аудитория 395", "Технопарк", 260f, 410f, NodeType.CLASSROOM, "395", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604",3),

        NodeEntity(UUID.randomUUID(), "Аудитория 404", "Лекционная аудитория", 150f, 300f, NodeType.CLASSROOM, "404", true, UUID.randomUUID(), "https://api.maptiler.com/maps/0196bbb3-c1fe-7bfb-8fa3-f8ce5f14ffb8/?key=pEC9gVZBA06hIDiYD3bk#17.4/55.76656/37.68577",4),
        NodeEntity(UUID.randomUUID(), "Аудитория 430", "Аудитория для групповых занятий", 160f, 310f, NodeType.CLASSROOM, "430", true, UUID.randomUUID(), "https://api.maptiler.com/maps/0196bbae-ed04-79d7-a6c0-40a04d3a5a0b/?key=pEC9gVZBA06hIDiYD3bk#17.4/55.76647/37.68655",4),
        // Столовые (2 элемента)
        NodeEntity(UUID.randomUUID(), "Кафетерий", "Зона питания для студентов", 550f, 900f, NodeType.CANTEEN, "C1", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635",3),
        NodeEntity(UUID.randomUUID(), "Ресторан в кампусе", "Меню для преподавателей и гостей", 570f, 920f, NodeType.CANTEEN, "C2", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604",3),

        // Лаборатории (2 элемента)
        NodeEntity(UUID.randomUUID(), "Лаборатория химии", "Оборудована для химических экспериментов ffffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffffffffffff ffffffffffffffffffffffffffffffff ffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffffff", 750f, 1100f, NodeType.LABORATORY, "L1", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604",3),
        NodeEntity(UUID.randomUUID(), "Физическая лаборатория", "Анализ физических процессов", 770f, 1120f, NodeType.LABORATORY, "L2", true, UUID.randomUUID(), "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635",3)
    )

    data class Routes(
        val from: String,
        val to: String,
        val routeLink: String,
        val emptyRouteLink: String,
        val floor: Int
    )

    val routes = listOf(
        Routes(
            "384",
            "389",
            "https://api.maptiler.com/maps/019695c6-45e9-791b-8d35-c0e01a58c539/?key=PHHZ2OozEcXHfqqJCqIr#18.51/55.7662563/37.6864668",
            "https://api.maptiler.com/maps/019695dc-dca6-7c87-a3c9-a343bd54b223/?key=PHHZ2OozEcXHfqqJCqIr#17.91/55.76625/37.686263",
            3
        ),
        Routes(
            "404",
            "430",
            "https://api.maptiler.com/maps/0196bbc0-7382-7a62-a88c-ad873513fbfa/?key=pEC9gVZBA06hIDiYD3bk#18.46/55.7665431/37.6864652",
            "https://api.maptiler.com/maps/0196bbb7-8191-7629-bc2d-92b2a41ca4e2/?key=pEC9gVZBA06hIDiYD3bk#17.4/55.76647/37.68621",
            4
    )



    )
}


