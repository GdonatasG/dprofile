package com.donatas.dprofile.features.contacts

import com.donatas.dprofile.feature.Screen
import org.koin.core.component.KoinComponent

actual class ContactsScreen actual constructor() : Screen, KoinComponent {
    private val factory = Factory()
    override fun controller(): NSViewController {
        throw NotImplementedError()
    }
}
