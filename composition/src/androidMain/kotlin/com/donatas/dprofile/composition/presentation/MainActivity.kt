package com.donatas.dprofile.composition.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import com.donatas.dprofile.compose.theme.AppTheme
import com.donatas.dprofile.composition.NavGraphs
import com.donatas.dprofile.composition.di.Scopes
import com.donatas.dprofile.composition.presentation.navigation.AppNavigation
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.core.component.KoinComponent

class MainActivity : FragmentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppTheme {
                val systemUIController: SystemUiController = rememberSystemUiController()
                val useDarkIcons: Boolean = !isSystemInDarkTheme()
                val backgroundColor = MaterialTheme.colorScheme.background

                SideEffect {
                    systemUIController.setStatusBarColor(
                        color = Color.Transparent, darkIcons = false
                    )
                    systemUIController.setNavigationBarColor(backgroundColor)
                }

                AppNavigation(
                    navGraph = NavGraphs.main
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            getKoin().getScope(Scopes.BOTTOM_TAB.name).close()
        } catch (_: Exception) {
        }
    }

}
