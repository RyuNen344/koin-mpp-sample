package com.ryunen344.koin.mpp.sample.data.api.reqres

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventListResponse(
    @SerialName("results_returned")
    val resultsReturned : Int,

    @SerialName("results_available")
    val resultsAvailable : Int,

    @SerialName("results_start")
    val resultsStart : Int,
    val events : List<EventResponse>
)
