package com.ryunen344.koin.mpp.sample.koin

import io.github.aakira.napier.Napier

class InstancePrinter {
    fun print() {
        Napier.d("$this")
    }
}
