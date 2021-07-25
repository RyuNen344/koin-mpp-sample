package com.ryunen344.koin.mpp.sample.napier

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@Suppress("UNUSED")
fun debugBuild() {
    Napier.base(DebugAntilog())
}

@Suppress("UNUSED")
fun releaseBuild(antilog : Antilog) {
    Napier.base(antilog)
}
