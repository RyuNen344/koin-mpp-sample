package com.ryunen344.koin.mpp.sample.di

import com.ryunen344.koin.mpp.sample.data.framework.Platform
import com.ryunen344.koin.mpp.sample.data.framework.impl.PlatformImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val frameworkModule = module {
    single { PlatformImpl() } bind Platform::class
}
