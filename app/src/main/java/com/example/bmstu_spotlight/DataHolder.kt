package com.example.bmstu_spotlight

object DataHolder {
    var location1: String = ""
    var location2: String = ""
    var showNewTopSection: Boolean = false

// Все ниже комментария удалим, когда появятся карты
    var markerPositions = listOf(
        Pair(100f, 200f), // Маркер 1
        Pair(400f, 300f), // Маркер 2
        Pair(250f, 550f), // Маркер 3
        Pair(600f, 750f)  // Маркер 4
    )
    var targetMarkerIndex: Int? = null // Индекс маркера, на который нужно переместиться
    var targetScale: Float = 2f // Степень увеличения при фокусировке на маркере
}
