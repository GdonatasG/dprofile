package com.donatas.dprofile.composition.navigation.delegate

import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.features.contacts.presentation.ContactsDelegate

class DefaultContactsDelegate(
    private val navigator: Navigator
) : ContactsDelegate {
    override fun onEmail() {
        TODO("Not yet implemented")
    }

    override fun onOpenBrowser(url: String) {
        navigator.openBrowser(url)
    }
}
