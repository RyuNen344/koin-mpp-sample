package com.ryunen344.koin.mpp.sample

import com.ryunen344.koin.mpp.sample.di.scopedModule
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import org.koin.test.mock.MockProviderRule

class CheckModuleTest : KoinTest {
    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun testExample() {
        checkKoinModules(scopedModule)
    }
}
