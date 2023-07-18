package com.donatas.dprofile.features.contacts.presentation

import com.donatas.dprofile.viewmodel.ViewModel

class ContactsViewModel(
    private val delegate: ContactsDelegate
) : ViewModel() {
    fun onEmail() = delegate.onEmail()
    fun onLinkedIn() = delegate.onOpenBrowser(url = "https://www.google.com")
    fun onUpwork() = delegate.onOpenBrowser(url = "https://www.google.com")
    fun onFreelancer() = delegate.onOpenBrowser(url = "https://www.google.com")
}
