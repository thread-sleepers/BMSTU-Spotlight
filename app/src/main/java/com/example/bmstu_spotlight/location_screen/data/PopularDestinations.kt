package com.example.bmstu_spotlight.location_screen.data

data class PopularDestinations(
    val name: String,
    val reference: String,
    val floor: Int
)

val popularTo: List<PopularDestinations> = listOf(
    PopularDestinations("389", "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604", 3),
    PopularDestinations("384", "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635", 3),
    PopularDestinations("430", "https://api.maptiler.com/maps/0196bbae-ed04-79d7-a6c0-40a04d3a5a0b/?key=pEC9gVZBA06hIDiYD3bk#18.53/55.7661876/37.6859258", 4),
    PopularDestinations("404", "https://api.maptiler.com/maps/0196bbb3-c1fe-7bfb-8fa3-f8ce5f14ffb8/?key=pEC9gVZBA06hIDiYD3bk#18.14/55.76669/37.686143", 4),

)

val popularFrom: List<PopularDestinations> = listOf(
    PopularDestinations("384", "https://api.maptiler.com/maps/019695d5-f77b-724d-9753-73c3109d9dc2/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76644/37.68635", 3),
    PopularDestinations("389", "https://api.maptiler.com/maps/019695d9-8677-7225-a491-7793fd64cf7f/?key=PHHZ2OozEcXHfqqJCqIr#17.7/55.76643/37.68604", 3),
    PopularDestinations("404", "https://api.maptiler.com/maps/0196bbb3-c1fe-7bfb-8fa3-f8ce5f14ffb8/?key=pEC9gVZBA06hIDiYD3bk#18.14/55.76669/37.686143", 4),
    PopularDestinations("430", "https://api.maptiler.com/maps/0196bbae-ed04-79d7-a6c0-40a04d3a5a0b/?key=pEC9gVZBA06hIDiYD3bk#18.53/55.7661876/37.6859258", 4),
)