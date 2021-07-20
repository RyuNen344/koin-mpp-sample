package com.ryunen344.koin.mpp.sample.data.framework.impl

import com.ryunen344.koin.mpp.sample.data.framework.Platform

internal expect class PlatformImpl : Platform {
    override val osName : String
    override val osVersion : String
}
