package com.ryunen344.koin.mpp.sample.data.api

import com.ryunen344.koin.mpp.sample.model.connpass.Event
import com.ryunen344.koin.mpp.sample.model.connpass.EventList

interface ConnpassApi {
    suspend fun getEvents(keyword : String? = null, count : Int = 50, start : Int = 0) : EventList
    suspend fun getEvent(eventId : Int) : Event
}
