package com.ryunen344.koin.mpp.sample.data.api.impl

import com.ryunen344.koin.mpp.sample.data.api.ConnpassApi
import com.ryunen344.koin.mpp.sample.data.api.reqres.EventListResponse
import com.ryunen344.koin.mpp.sample.data.api.reqres.EventResponse
import com.ryunen344.koin.mpp.sample.data.api.reqres.GroupResponse
import com.ryunen344.koin.mpp.sample.model.connpass.Event
import com.ryunen344.koin.mpp.sample.model.connpass.EventList
import com.ryunen344.koin.mpp.sample.model.connpass.Group
import io.ktor.client.*
import io.ktor.client.request.*

internal class ConnpassApiImpl(private val httpClient : HttpClient) : ConnpassApi {

    private val endpoint = "https://connpass.com/api/v1/event"

    override suspend fun getEvents(keyword : String?, count : Int, start : Int) : EventList {
        return httpClient.get<EventListResponse>(endpoint) {
            keyword?.let { parameter("keyword", it) }
            parameter("count", count)
            parameter("start", start)
        }.toModel()
    }

    override suspend fun getEvent(eventId : Int) : Event {
        return httpClient.get<EventResponse>(endpoint) {
            parameter("eventId", eventId)
        }.toModel()
    }

    private fun EventListResponse.toModel() : EventList {
        return EventList(
            resultsReturned = resultsReturned,
            resultsAvailable = resultsAvailable,
            resultsStart = resultsStart,
            events = events.map { it.toModel() }
        )
    }

    private fun EventResponse.toModel() : Event {
        return Event(
            eventId = eventId,
            title = title,
            catch = catch,
            description = description,
            eventUrl = eventUrl,
            hashTag = hashTag,
            limit = limit,
            eventType = eventType,
            series = series?.toModel(),
            address = address,
            place = place,
            lat = lat,
            lon = lon,
            ownerId = ownerId,
            ownerNickname = ownerNickname,
            ownerDisplayName = ownerDisplayName,
            accepted = accepted,
            waiting = waiting
        )
    }

    private fun GroupResponse.toModel() : Group {
        return Group(
            id = id,
            title = title,
            url = url
        )
    }
}
