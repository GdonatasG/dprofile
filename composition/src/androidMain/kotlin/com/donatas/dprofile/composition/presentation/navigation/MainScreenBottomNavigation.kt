package com.donatas.dprofile.composition.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.provider.LocalParentNavController
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.presentation.BottomTab
import com.donatas.dprofile.composition.presentation.screen.modalRoute

@Composable
fun MainScreenBottomNavigation(
    bottomNavController: NavHostController,
    appTutorial: AppTutorial,
    screen: @Composable () -> Unit,
    onActivityDestroyed: () -> Unit
) {

    PrivateBackHandler(
        bottomNavController = bottomNavController,
        appTutorial = appTutorial,
        onActivityDestroyed = onActivityDestroyed
    )

    NavHost(
        navController = bottomNavController, startDestination = BottomTab.Type.ABOUT_ME.route
    ) {
        composable(BottomTab.Type.ABOUT_ME.route) {
            screen()
        }
        composable(BottomTab.Type.GITHUB.route) {
            screen()
        }
        composable(BottomTab.Type.CONTACTS.route) {
            screen()
        }
    }
}

@Composable
private fun PrivateBackHandler(
    bottomNavController: NavHostController,
    appTutorial: AppTutorial,
    onActivityDestroyed: () -> Unit
) {
    val tutorialState by appTutorial.state.collectAsState()
    val activity = (LocalContext.current as? Activity)
    val parentNavController: NavController = LocalParentNavController.current

    BackHandler {
        if (parentNavController.currentDestination!!.route == modalRoute) {
            parentNavController.navigateUp()

            return@BackHandler
        }

        val didPop: Boolean = if (!tutorialState.isFinished) {
            appTutorial.previous()
        } else {
            bottomNavController.popBackStack()
        }

//        val didPop: Boolean  = bottomNavController.popBackStack()

        if (!didPop) {
            onActivityDestroyed()
            activity?.finish()
        }
    }
}
