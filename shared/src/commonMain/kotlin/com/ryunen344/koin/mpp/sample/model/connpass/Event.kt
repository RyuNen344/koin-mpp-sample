package com.ryunen344.koin.mpp.sample.model.connpass

data class Event(
    val eventId : Int,
    val title : String,
    val catch : String,
    val description : String,
    val eventUrl : String,
    val hashTag : String,
    val limit : Int?,
    val eventType : String,
    val series : Group?,
    val address : String?,
    val place : String?,
    val lat : Float?,
    val lon : Float?,
    val ownerId : Int,
    val ownerNickname : String,
    val ownerDisplayName : String,
    val accepted : Int,
    val waiting : Int
)
