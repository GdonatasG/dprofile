package com.donatas.dprofile.composition

import android.app.Application
import com.donatas.dprofile.composition.di.commonModules
import com.donatas.dprofile.composition.di.navigationModule
import com.donatas.dprofile.composition.navigation.ComposeModalFactory
import com.donatas.dprofile.composition.navigation.ComposeScreenFactory
import com.donatas.dprofile.utils.isDebug
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import org.koin.core.module.Module

actual open class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        if (isDebug) {
            Napier.base(DebugAntilog())
        }

        startKoin {
            allowOverride(true)
            androidContext(this@App)

            val modules = mutableListOf<Module>(navigationModule)

            modules.addAll(
                commonModules(
                    screenFactory = ComposeScreenFactory(),
                    modalFactory = ComposeModalFactory()
                )
            )

            modules(modules)
        }
    }
}
