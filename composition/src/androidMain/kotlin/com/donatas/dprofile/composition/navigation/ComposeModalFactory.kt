package com.donatas.dprofile.composition.navigation

import com.donatas.dprofile.composition.navigation.core.ModalFactory
import com.donatas.dprofile.composition.navigation.screens.FiltersModalFactory
import com.donatas.dprofile.composition.navigation.screens.components.filters.DefaultFiltersModalFactory

class ComposeModalFactory : ModalFactory {
    override fun filters(): FiltersModalFactory {
       return DefaultFiltersModalFactory()
    }

}
