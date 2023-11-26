package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.contacts.ContactsViewModel

expect interface ContactsScreenFactory

expect class ContactsScreen constructor(
    factory: ContactsScreenFactory,
    viewModel: ContactsViewModel,
    appTutorial: AppTutorial
) : Screen
