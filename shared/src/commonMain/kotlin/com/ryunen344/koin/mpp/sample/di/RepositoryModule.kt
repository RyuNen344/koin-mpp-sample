package com.ryunen344.koin.mpp.sample.di

import com.ryunen344.koin.mpp.sample.repository.SettingsRepository
import com.ryunen344.koin.mpp.sample.repository.impl.SettingsRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { SettingsRepositoryImpl(get(), get()) } bind SettingsRepository::class
}
