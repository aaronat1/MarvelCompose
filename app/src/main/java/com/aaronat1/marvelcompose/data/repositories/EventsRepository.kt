package com.aaronat1.marvelcompose.data.repositories

import com.aaronat1.marvelcompose.data.entities.Event
import com.aaronat1.marvelcompose.data.entities.Result
import com.aaronat1.marvelcompose.data.network.ApiClient

object EventsRepository : Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        ApiClient
            .eventsService
            .getEvents(0, 100)
            .data
            .results
            .map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
            ApiClient
                .eventsService
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}
