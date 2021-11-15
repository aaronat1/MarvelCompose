package com.aaronat1.marvelcompose.data.network

import com.aaronat1.marvelcompose.data.network.entities.ApiResponse
import com.aaronat1.marvelcompose.data.network.entities.ApiComic
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicsService {

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("format") format: String?
    ): ApiResponse<ApiComic>

    @GET("/v1/public/comics/{comicId}")
    suspend fun findComic(
        @Path("comicId") characterId: Int
    ) : ApiResponse<ApiComic>

}