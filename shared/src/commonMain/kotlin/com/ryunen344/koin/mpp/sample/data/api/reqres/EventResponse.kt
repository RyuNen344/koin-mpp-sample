package com.ryunen344.koin.mpp.sample.data.api.reqres

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
    @SerialName("event_id")
    val eventId : Int,
    val title : String,
    val catch : String,
    val description : String,

    @SerialName("event_url")
    val eventUrl : String,

    @SerialName("hash_tag")
    val hashTag : String,
    val limit : Int?,

    @SerialName("event_type")
    val eventType : String,
    val series : GroupResponse?,
    val address : String?,
    val place : String?,
    val lat : Float?,
    val lon : Float?,

    @SerialName("owner_id")
    val ownerId : Int,

    @SerialName("owner_nickname")
    val ownerNickname : String,

    @SerialName("owner_display_name")
    val ownerDisplayName : String,
    val accepted : Int,
    val waiting : Int
)
