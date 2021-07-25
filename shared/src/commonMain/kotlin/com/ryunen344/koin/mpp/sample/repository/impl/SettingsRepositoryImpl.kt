package com.ryunen344.koin.mpp.sample.repository.impl

import com.ryunen344.koin.mpp.sample.data.framework.Platform
import com.ryunen344.koin.mpp.sample.repository.SettingsRepository

internal class SettingsRepositoryImpl(private val platform : Platform) : SettingsRepository {
    override fun getSystemInfo() : String {
        return platform.osName + platform.osVersion
    }
}
