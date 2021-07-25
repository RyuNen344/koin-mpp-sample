package com.ryunen344.koin.mpp.sample.koin

import io.github.aakira.napier.Napier
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.logger.KOIN_TAG
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

@OptIn(KoinInternalApi::class)
fun KoinApplication.napierLogger(
    level : Level = Level.INFO,
) : KoinApplication {
    koin.setupLogger(NapierLogger(level))
    return this
}

internal class NapierLogger(level : Level = Level.INFO) : Logger(level) {
    override fun log(level : Level, msg : MESSAGE) {
        if (this.level <= level) {
            logOnLevel(msg, level)
        }
    }

    private fun logOnLevel(msg : MESSAGE, level : Level) {
        when (level) {
            Level.DEBUG -> Napier.d(msg, tag = KOIN_TAG)
            Level.INFO -> Napier.i(msg, tag = KOIN_TAG)
            Level.ERROR -> Napier.e(msg, tag = KOIN_TAG)
            else -> Napier.e(msg, tag = KOIN_TAG)
        }
    }
}
