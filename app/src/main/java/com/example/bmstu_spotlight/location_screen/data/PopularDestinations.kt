package com.example.bmstu_spotlight.location_screen.data

data class PopularDestinations(
    val name: String,
    val reference: String
)

val popularTo: List<PopularDestinations> = listOf(
    PopularDestinations("389", "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604"),
    PopularDestinations("384", "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635"),

)

val popularFrom: List<PopularDestinations> = listOf(
    PopularDestinations("384", "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635"),
    PopularDestinations("389", "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604"),
)