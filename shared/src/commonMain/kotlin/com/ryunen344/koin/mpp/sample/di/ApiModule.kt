package com.ryunen344.koin.mpp.sample.di

import com.ryunen344.koin.mpp.sample.data.api.ConnpassApi
import com.ryunen344.koin.mpp.sample.data.api.impl.ConnpassApiImpl
import com.ryunen344.koin.mpp.sample.data.api.impl.HttpClientProviderImpl
import io.ktor.client.*
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val apiModule : Module = module {
    single { HttpClientProviderImpl(get(), get()).providerHttpClient() } bind HttpClient::class
    single { ConnpassApiImpl(get()) } bind ConnpassApi::class
    single {
        Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }
    }
}
