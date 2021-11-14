package com.ryunen344.koin.mpp.sample.data.framework.impl

import com.ryunen344.koin.mpp.sample.data.framework.Platform
import platform.UIKit.UIDevice

internal actual class PlatformImpl : Platform {
    actual override val osName : String
        get() = UIDevice.currentDevice.systemName
    actual override val osVersion : String
        get() = UIDevice.currentDevice.systemVersion
}
