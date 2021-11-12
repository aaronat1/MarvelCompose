package com.aaronat1.marvelcompose.data.network.entities

data class ApiCharacter(
    val comics: ApiComics,
    val description: String,
    val events: ApiEvents,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: ApiSeries,
    val stories: ApiStories,
    val thumbnail: Thumbnail,
    val urls: List<ApiUrl>
)
