package com.aaronat1.marvelcompose.data.network.entities

data class ApiComics(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiComic>,
    val returned: Int
)