package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.contacts.ContactsViewModel
import org.koin.core.scope.Scope

expect interface ContactsScreenFactory

expect class ContactsScreen constructor(
    scope: Scope,
    factory: ContactsScreenFactory,
    appTutorial: AppTutorial
) : Screen
