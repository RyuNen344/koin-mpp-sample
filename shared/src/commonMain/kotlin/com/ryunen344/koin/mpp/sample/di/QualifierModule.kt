package com.ryunen344.koin.mpp.sample.di

import com.ryunen344.koin.mpp.sample.koin.InstancePrinter
import com.ryunen344.koin.mpp.sample.koin.qualifier.KlassQualifier
import com.ryunen344.koin.mpp.sample.koin.qualifier.Mobile
import com.ryunen344.koin.mpp.sample.koin.qualifier.namedQualifier
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val qualifierModule = module {
    // 素のsingleton
    single { InstancePrinter() }

    // string
    single(named(namedQualifier)) {
        InstancePrinter()
    }

    // type
    single(qualifier<KlassQualifier>()) {
        InstancePrinter()
    }

    // enum
    single(Mobile.IOS.qualifier) {
        InstancePrinter()
    }

    single(Mobile.ANDROID.qualifier) {
        InstancePrinter()
    }
}
