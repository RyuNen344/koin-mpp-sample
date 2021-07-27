package com.ryunen344.koin.mpp.sample.di

import com.ryunen344.koin.mpp.sample.koin.InstancePrinter
import com.ryunen344.koin.mpp.sample.koin.scope.ScopeOwner
import org.koin.dsl.module

val scopedModule = module {
    // scopeの持ち主
    scope<ScopeOwner> {
        // scope毎に生成されるinstance
        scoped { InstancePrinter() }
    }

    // androidのviewModelはviewModelStoreOwnerの
}
