package com.ryunen344.koin.mpp.sample.di

import io.ktor.client.engine.*
import org.koin.core.module.Module

/**
 * expect, actualを用いて定義する各OSごとのmodule
 *
 * [HttpClientEngine]
 */
expect val expectModule : Module
