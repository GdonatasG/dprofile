package com.donatas.dprofile.composition.di.extensions

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.donatas.dprofile.composition.components.locals.getNavController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

@Composable
inline fun <reified T : ViewModel> getNavViewModel(
    scope: Scope,
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    val navController = getNavController()
    navController.currentBackStackEntry?.let { entry ->
        return getViewModel(
            scope = scope,
            qualifier = qualifier,
            parameters = parameters,
            owner = entry
        )
    }

    throw IllegalStateException("currentBackStackEntry of NavHostController not found")
}
