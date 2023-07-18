package com.donatas.dprofile.compose.provider

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalParentNavController = compositionLocalOf<NavController> { error("No NavController found!") }
