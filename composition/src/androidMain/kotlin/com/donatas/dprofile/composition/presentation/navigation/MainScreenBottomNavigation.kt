package com.donatas.dprofile.composition.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.provider.LocalParentNavController
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.components.locals.LocalNavController
import com.donatas.dprofile.composition.presentation.BottomTab
import com.donatas.dprofile.composition.presentation.screen.modalRoute
import com.ramcosta.composedestinations.utils.route

@Composable
fun MainScreenBottomNavigation(
    bottomNavController: NavHostController,
    appTutorial: AppTutorial,
    tabs: List<BottomTab>,
    screen: @Composable () -> Unit,
    onActivityDestroyed: () -> Unit,
    onChanged: (tab: BottomTab) -> Unit
) {

    PrivateBackHandler(
        bottomNavController = bottomNavController,
        appTutorial = appTutorial,
        onActivityDestroyed = onActivityDestroyed,
        tabs = tabs,
        onChanged = onChanged
    )

    CompositionLocalProvider(
        LocalNavController provides bottomNavController
    ) {
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
}

@Composable
private fun PrivateBackHandler(
    bottomNavController: NavHostController,
    appTutorial: AppTutorial,
    onActivityDestroyed: () -> Unit,
    tabs: List<BottomTab>,
    onChanged: (tab: BottomTab) -> Unit,
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
            bottomNavController.navigateUp()
        }

        if (!didPop) {
            onActivityDestroyed()
            activity?.finish()

            return@BackHandler
        }

        if (tutorialState.isFinished) {
            val currentRoute: String? = bottomNavController.currentBackStack.value.lastOrNull()?.destination?.route
            currentRoute?.let { route ->
                onChanged(tabs.first { it.type.route == route })
            }
        }
    }
}
