package com.ryunen344.koin.mpp.sample.koin.scope

import com.ryunen344.koin.mpp.sample.koin.InstancePrinter
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class ScopeOwner : KoinScopeComponent {
    override val scope : Scope by lazy { createScope(this) }

    private val instancePrinter : InstancePrinter by inject()

    fun printInstanceHash() {
        instancePrinter.print()
    }

    fun close() {
        scope.close()
    }
}
