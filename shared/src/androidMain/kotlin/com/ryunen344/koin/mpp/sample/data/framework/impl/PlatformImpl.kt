package com.ryunen344.koin.mpp.sample.data.framework.impl

import com.ryunen344.koin.mpp.sample.data.framework.Platform

internal actual class PlatformImpl : Platform {
    actual override val osName : String
        get() = android.os.Build.VERSION.CODENAME
    actual override val osVersion : String
        get() = android.os.Build.VERSION.SDK_INT.toString()
}
