package com.ryunen344.koin.mpp.sample.data.api.impl

import com.ryunen344.koin.mpp.sample.data.api.HttpClientProvider
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json

internal class HttpClientProviderImpl(
    private val json : Json,
    private val httpClientEngine : HttpClientEngine
) : HttpClientProvider {
    override fun providerHttpClient() : HttpClient {
        return HttpClient(httpClientEngine) {
            install(HttpTimeout) {
                requestTimeoutMillis = 60000L
                connectTimeoutMillis = 60000L
                socketTimeoutMillis = 60000L
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message : String) {
                        Napier.d("HttpClient : $message")
                    }
                }
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }
        }
    }
}
