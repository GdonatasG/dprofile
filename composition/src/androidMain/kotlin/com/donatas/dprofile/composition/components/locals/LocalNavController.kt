package com.donatas.dprofile.composition.components.locals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

@PublishedApi
internal val LocalNavController = compositionLocalOf<NavHostController> {
    error("Current Composable is not wrapped in NavHostController")
}

@Composable
fun getNavController(): NavHostController {
    return LocalNavController.current
}
