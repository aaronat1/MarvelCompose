package com.aaronat1.marvelcompose.data.network

import com.aaronat1.marvelcompose.data.network.entities.ApiResponse
import com.aaronat1.marvelcompose.data.network.entities.ApiCharacter
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<ApiCharacter>

    @GET("/v1/public/characters/{characterId}")
    suspend fun findCharacter(
        @Path("characterId") characterId: Int
    ) : ApiResponse<ApiCharacter>

}