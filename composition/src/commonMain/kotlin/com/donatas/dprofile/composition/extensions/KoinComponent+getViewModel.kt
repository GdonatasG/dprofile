package com.donatas.dprofile.composition.extensions

import com.donatas.dprofile.viewmodel.ViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.scope.Scope

expect inline fun <reified T : ViewModel> Scope.getViewModel(): T

expect inline fun <reified T : ViewModel> Scope.getViewModel(noinline parameters: ParametersDefinition?): T
