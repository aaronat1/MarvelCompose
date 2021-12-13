package com.aaronat1.marvelcompose.data.repositories

import com.aaronat1.marvelcompose.data.entities.Comic
import com.aaronat1.marvelcompose.data.entities.Result
import com.aaronat1.marvelcompose.data.entities.tryCall
import com.aaronat1.marvelcompose.data.network.ApiClient

object ComicsRepository  {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        ApiClient
            .comicsService
            .getComics(0, 20, format?.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }

    suspend fun find(id: Int): Result<Comic> = tryCall {
        ApiClient
            .comicsService
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }

}