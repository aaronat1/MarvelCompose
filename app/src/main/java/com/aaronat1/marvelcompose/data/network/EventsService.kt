package com.aaronat1.marvelcompose.data.network

import com.aaronat1.marvelcompose.data.network.entities.ApiResponse
import com.aaronat1.marvelcompose.data.network.entities.ApiEvent
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsService {
    @GET("/v1/public/events")
    suspend fun getEvents(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<ApiEvent>

    @GET("/v1/public/events/{eventId}")
    suspend fun findEvent(
        @Path("eventId") eventId: Int,
    ): ApiResponse<ApiEvent>
}