package com.ryunen344.koin.mpp.sample.android

import android.app.Application
import com.ryunen344.koin.mpp.sample.di.apiModule
import com.ryunen344.koin.mpp.sample.di.expectModule
import com.ryunen344.koin.mpp.sample.di.frameworkModule
import com.ryunen344.koin.mpp.sample.di.qualifierModule
import com.ryunen344.koin.mpp.sample.di.repositoryModule
import com.ryunen344.koin.mpp.sample.di.scopedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(
                module {
                    viewModel<MainViewModel>()
                },
                apiModule,
                expectModule,
                frameworkModule,
                qualifierModule,
                repositoryModule,
                scopedModule
            )
        }
    }
}
