package com.ryunen344.koin.mpp.sample.repository.impl

import com.ryunen344.koin.mpp.sample.data.api.ConnpassApi
import com.ryunen344.koin.mpp.sample.data.framework.Platform
import com.ryunen344.koin.mpp.sample.repository.SettingsRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class SettingsRepositoryImpl(
    private val platform : Platform,
    private val connpassApi : ConnpassApi,
    dispatcher : CoroutineDispatcher = Dispatchers.Default
) : SettingsRepository {

    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    override fun getSystemInfo() : String {
        return platform.osName + platform.osVersion
    }

    fun hoge() {
        scope.launch {
            runCatching {
                connpassApi.getEvents()
            }.onSuccess {
                Napier.d("$it")
            }.onFailure {
                Napier.e("failed", it)
            }
        }
    }
}
