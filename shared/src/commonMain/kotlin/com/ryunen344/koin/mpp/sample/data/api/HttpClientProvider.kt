package com.ryunen344.koin.mpp.sample.data.api

import io.ktor.client.*

interface HttpClientProvider {
    fun providerHttpClient() : HttpClient
}
