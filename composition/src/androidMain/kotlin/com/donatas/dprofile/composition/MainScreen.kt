package com.donatas.dprofile.composition

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.composition.presentation.navigation.BottomNavBar
import com.donatas.dprofile.composition.presentation.navigation.MainScreenBottomNavigation
import com.donatas.dprofile.composition.presentation.navigation.Tab
import com.donatas.dprofile.feature.Screen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

actual class MainScreen() : Screen {
    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Compose() {
        val tabs: List<Tab> = listOf(
            Tab.ABOUT_ME,
            Tab.GITHUB,
            Tab.CONTACTS
        )
        val bottomNavController = rememberAnimatedNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavBar(navController = bottomNavController, tabs = tabs)
            }
        ) {
            MainScreenBottomNavigation(
                bottomNavController = bottomNavController
            )

        }
    }
}
