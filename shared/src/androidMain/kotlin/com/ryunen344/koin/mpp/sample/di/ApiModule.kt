package com.ryunen344.koin.mpp.sample.di

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val httpClientEngineModule : Module = module {
    single { OkHttp.create() } bind HttpClientEngine::class
}
