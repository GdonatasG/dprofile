package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.features.contacts.presentation.ContactsDelegate

class DefaultContactsDelegate(
    private val navigator: Navigator
) : ContactsDelegate {
    override fun onOpenBrowser(uri: String) {
        navigator.openBrowser(uri)
    }
}
