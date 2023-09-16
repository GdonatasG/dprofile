package com.donatas.dprofile.composition.extensions

import com.donatas.dprofile.viewmodel.ViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.scope.Scope

actual inline fun <reified T : ViewModel> Scope.getViewModel(): T = this.get()

actual inline fun <reified T : ViewModel> Scope.getViewModel(noinline parameters: ParametersDefinition?): T =
    this.get(parameters = parameters)
