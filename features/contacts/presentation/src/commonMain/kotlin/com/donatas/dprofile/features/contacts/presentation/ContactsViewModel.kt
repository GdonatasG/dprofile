package com.donatas.dprofile.features.contacts.presentation

import com.donatas.dprofile.viewmodel.ViewModel

class ContactsViewModel(
    private val delegate: ContactsDelegate
) : ViewModel() {
    fun onEmail() = delegate.onOpenBrowser(uri = "mailto:zitkusd@gmail.com")
    fun onLinkedIn() = delegate.onOpenBrowser(uri = "https://www.linkedin.com/in/donatas-%C5%BEitkus-50a4b6163/")
    fun onUpwork() = delegate.onOpenBrowser(uri = "https://www.upwork.com/freelancers/~01d46d21775488abb6")
    fun onFreelancer() = delegate.onOpenBrowser(uri = "https://www.freelancer.com/u/zitkusd")
}
