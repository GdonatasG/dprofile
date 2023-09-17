package com.donatas.dprofile.composition.extensions

import androidx.compose.runtime.Composable
import com.donatas.dprofile.viewmodel.ViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.scope.Scope

@Composable
actual inline fun <reified T : ViewModel> Scope.getViewModel(): T = org.koin.androidx.compose.getViewModel(scope = this)

@Composable
actual inline fun <reified T : ViewModel> Scope.getViewModel(noinline parameters: ParametersDefinition?): T =
    org.koin.androidx.compose.getViewModel(scope = this, parameters = parameters)
