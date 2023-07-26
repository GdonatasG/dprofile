package com.donatas.dprofile.composition.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.components.animation.EnterAnimation
import com.donatas.dprofile.compose.provider.LocalParentNavController
import com.donatas.dprofile.composition.presentation.screen.modalRoute
import com.donatas.dprofile.feature.Components
import com.donatas.dprofile.features.aboutme.AboutMeFeature
import com.donatas.dprofile.features.contacts.ContactsFeature
import com.donatas.dprofile.features.github.GithubFeature
import org.koin.androidx.compose.get

@Composable
fun MainScreenBottomNavigation(
    bottomNavController: NavHostController
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
            activity?.finish()
        }
    }

    NavHost(
        navController = bottomNavController, startDestination = Tab.ABOUT_ME.route
    ) {
        composable(Tab.ABOUT_ME.route) {
            EnterAnimation {
                get<AboutMeFeature>().screen().Compose(components = Components())
            }
        }
        composable(Tab.GITHUB.route) {
            EnterAnimation {
                get<GithubFeature>().screen().Compose(components = Components())
            }
        }
        composable(Tab.CONTACTS.route) {
            EnterAnimation {
                get<ContactsFeature>().screen().Compose(components = Components())
            }
        }
    }
}
