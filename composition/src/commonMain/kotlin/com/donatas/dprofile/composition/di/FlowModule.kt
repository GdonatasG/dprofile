package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.MainScreen
import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.composition.navigation.flow.MainFlow
import org.koin.core.module.Module
import org.koin.dsl.module

internal val flowModule: Module = module {
    single<MainFlow> {
        MainFlow(
            navigator = get<Navigator>(),
            mainScreen = get<MainScreen>()
        )
    }
}
