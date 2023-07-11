package com.donatas.dprofile.composition.di

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.composition.MainScreen
import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.composition.presentation.navigation.AlertController
import com.donatas.dprofile.composition.presentation.navigation.AlertControllerAdapter
import com.donatas.dprofile.composition.presentation.navigation.AlertState
import com.donatas.dprofile.composition.presentation.navigation.AlertStateAdapter
import com.donatas.dprofile.composition.presentation.navigation.AndroidAlertNavigator
import com.donatas.dprofile.composition.presentation.navigation.AndroidAppNavigator
import com.donatas.dprofile.composition.presentation.navigation.AndroidNavigator
import com.donatas.dprofile.composition.presentation.navigation.ModalState
import com.donatas.dprofile.composition.presentation.navigation.ModalStateAdapter
import com.donatas.dprofile.composition.presentation.navigation.PlatformNavigator
import org.koin.dsl.module

internal val navigationModule = module {
    single<ModalState> { ModalStateAdapter() }
    single<AlertState> { AlertStateAdapter() }
    single<AlertController> { AlertControllerAdapter(alertState = get()) }
    single<Alert.Coordinator>() {
        AndroidAlertNavigator(
            alert = get<AlertController>()
        )
    }
    single<AndroidNavigator>() {
        AndroidAppNavigator(
            modalState = get<ModalState>()
        )
    }
    single<Navigator> {
        PlatformNavigator(
            androidNavigator = get<AndroidNavigator>()
        )
    }
    factory<MainScreen> {
        MainScreen()
    }
}
