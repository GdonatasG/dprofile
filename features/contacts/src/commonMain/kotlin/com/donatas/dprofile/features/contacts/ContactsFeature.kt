package com.donatas.dprofile.features.contacts

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ContactsFeature(): KoinComponent {
    init {
        loadModules()
    }

    fun screen(): ContactsScreen = get<ContactsScreen>()
}
