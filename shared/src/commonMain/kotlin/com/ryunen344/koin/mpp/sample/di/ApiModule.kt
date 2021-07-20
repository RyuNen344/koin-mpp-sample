package com.ryunen344.koin.mpp.sample.di

import com.ryunen344.koin.mpp.sample.data.api.UserApi
import com.ryunen344.koin.mpp.sample.data.api.impl.HttpClientProviderImpl
import com.ryunen344.koin.mpp.sample.data.api.impl.UserApiImpl
import io.ktor.client.*
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val apiModule : Module = module {
    single { HttpClientProviderImpl(get(), get()).providerHttpClient() } bind HttpClient::class
    single { UserApiImpl(get()) } bind UserApi::class
}
