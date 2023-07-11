package com.donatas.dprofile.composition.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.composition.navigation.flow.MainFlow
import com.donatas.dprofile.composition.presentation.screen.destinations.AlertDestination
import com.donatas.dprofile.composition.presentation.screen.destinations.ModalDestination
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.spec.NavGraphSpec
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
internal fun AppNavigation(
    navigator: AndroidNavigator = get(),
    alertController: AlertController = get(),
    mainFlow: MainFlow = get(),
    navGraph: NavGraphSpec
) {
    val navEngine =
        rememberAnimatedNavHostEngine(rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING)
    val sheetState = androidx.compose.material.rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val bottomSheetNavigator = remember { BottomSheetNavigator(sheetState) }
    val navHostController = navEngine.rememberNavController(bottomSheetNavigator)

    val navAction by navigator.navAction.collectAsState()
    val modalAction by navigator.modalAction.collectAsState()
    val navigateBack by navigator.navigateBackAction.collectAsState()

    val alertAction by alertController.navigationAction.collectAsState()
    val closeAlertAction by alertController.closeAlert.collectAsState()

    LaunchedEffect(true) {
        mainFlow.start()
    }

    LaunchedEffect(alertAction) {
        alertAction?.let {
            navigator.navigate(NavigationAction(AlertDestination))
            alertController.resetNavigationAction()
        }
    }

    LaunchedEffect(closeAlertAction) {
        closeAlertAction?.let {
            navHostController.popBackStack()
            alertController.resetCloseAction()
        }
    }

    LaunchedEffect(navigateBack) {
        navigateBack?.let {
            navHostController.navigateUp()
            navigator.resetModalAction()
        }
    }

    LaunchedEffect(modalAction) {
        modalAction?.let {
            navigator.navigate(
                NavigationAction(
                    destination = ModalDestination,
                    navOptions = AppNavOptions.Builder().setLaunchSingleTop(true).build()
                )
            )
            navigator.resetModalAction()
        }
    }

    LaunchedEffect(navAction) {
        navAction?.let { action ->
            if (action.navOptions.popUpAllInclusive) {
                while (navHostController.popBackStack()) {
                }
            }
            navHostController.navigate(
                route = action.destination.route,
                navOptions = action.navOptions.toNavOptions()
            )
            navigator.resetNavigationAction()
        }
    }

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetShape = RoundedCornerShape(
            topEnd = 14.dp, topStart = 14.dp
        )
    ) {
        Scaffold {
            DestinationsNavHost(
                navGraph = navGraph,
                engine = navEngine,
                navController = navHostController
            )
        }
    }
}
