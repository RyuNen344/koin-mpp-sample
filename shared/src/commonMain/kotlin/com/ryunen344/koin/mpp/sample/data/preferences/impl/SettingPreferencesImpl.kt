package com.ryunen344.koin.mpp.sample.data.preferences.impl

import com.ryunen344.koin.mpp.sample.data.preferences.SettingPreferences
import com.ryunen344.koin.mpp.sample.model.Theme

internal class SettingPreferencesImpl : SettingPreferences {
    override val theme : Theme
        get() = Theme.DARK
}
