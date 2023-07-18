package com.donatas.dprofile.features.filter

import com.donatas.dprofile.feature.Modal
import org.koin.core.component.KoinComponent

actual class FilterModal actual constructor() : Modal, KoinComponent {
    private val factory = Factory()
    override fun controller(): NSViewController {
        throw NotImplementedError()
    }
}
