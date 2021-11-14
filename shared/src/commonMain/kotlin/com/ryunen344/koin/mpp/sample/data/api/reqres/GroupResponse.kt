package com.ryunen344.koin.mpp.sample.data.api.reqres

import kotlinx.serialization.Serializable

@Serializable
data class GroupResponse(
    val id : Int,
    val title : String,
    val url : String,
)
