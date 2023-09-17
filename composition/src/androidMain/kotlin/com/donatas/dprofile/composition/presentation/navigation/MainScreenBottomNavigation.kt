package com.donatas.dprofile.composition.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.provider.LocalParentNavController
import com.donatas.dprofile.composition.presentation.BottomTab
import com.donatas.dprofile.composition.presentation.screen.modalRoute

@Composable
fun MainScreenBottomNavigation(
    bottomNavController: NavHostController,
    screen: @Composable () -> Unit,
    onActivityDestroyed: () -> Unit
) {

    PrivateBackHandler(
        bottomNavController = bottomNavController,
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
    onActivityDestroyed: () -> Unit
) {
    val activity = (LocalContext.current as? Activity)
    val parentNavController: NavController = LocalParentNavController.current

    BackHandler {
        if (parentNavController.currentDestination!!.route == modalRoute) {
            parentNavController.navigateUp()

            return@BackHandler
        }

        val didPop = bottomNavController.popBackStack()

        if (!didPop) {
            onActivityDestroyed()
            activity?.finish()
        }
    }
}
