package com.donatas.dprofile.features.contacts

import com.donatas.dprofile.features.contacts.presentation.ContactsDelegate
import com.donatas.dprofile.features.contacts.presentation.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<ContactsViewModel>() {
        ContactsViewModel(
            delegate = get<ContactsDelegate>()
        )
    }
}
