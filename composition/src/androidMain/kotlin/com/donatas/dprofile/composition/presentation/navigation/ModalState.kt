package com.donatas.dprofile.composition.presentation.navigation

import com.donatas.dprofile.feature.Modal

internal interface ModalState {
    fun setModal(modal: Modal)
    fun getModal(): Modal?
}

internal class ModalStateAdapter : ModalState {
    private var modal: Modal? = null

    override fun setModal(modal: Modal) {
        this.modal = modal
    }

    override fun getModal(): Modal? = modal
}
