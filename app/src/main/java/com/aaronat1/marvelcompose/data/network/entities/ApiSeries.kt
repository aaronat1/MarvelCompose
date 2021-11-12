package com.aaronat1.marvelcompose.data.network.entities

data class ApiSeries(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiSerie>,
    val returned: Int
)