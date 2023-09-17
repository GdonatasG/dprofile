package com.donatas.dprofile.composition.navigation.core

import com.donatas.dprofile.composition.navigation.screens.FiltersModalFactory

interface ModalFactory {
    fun filters(): FiltersModalFactory
}
