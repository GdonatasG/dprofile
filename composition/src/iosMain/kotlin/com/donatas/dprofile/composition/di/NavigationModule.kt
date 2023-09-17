package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.MainScreen
import com.donatas.dprofile.composition.UIKitNavigator
import com.donatas.dprofile.composition.navigation.core.Navigator
import org.koin.dsl.module
import platform.UIKit.UINavigationController

internal val navigationModule = module {
    single<UINavigationControllerWrapper> {
        val controller = UINavigationController()
        controller.setNavigationBarHidden(true)
        UINavigationControllerWrapper(controller)
    }

    single<Navigator> {
        UIKitNavigator(get<UINavigationControllerWrapper>())
    }

    factory<MainScreen> {
        MainScreen()
    }
}

internal data class UINavigationControllerWrapper(val shared: UINavigationController)

