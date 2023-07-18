package com.donatas.dprofile.features.contacts.presentation

interface ContactsDelegate {
    fun onEmail()
    fun onOpenBrowser(url: String)
}
