package com.ryunen344.koin.mpp.sample.data.api.impl

import com.ryunen344.koin.mpp.sample.data.api.UserApi
import io.ktor.client.*

internal class UserApiImpl(private val httpClient : HttpClient) : UserApi {
}
