package com.ryunen344.koin.mpp.sample.model.connpass

data class EventList(
    val resultsReturned : Int,
    val resultsAvailable : Int,
    val resultsStart : Int,
    val events : List<Event>
)
