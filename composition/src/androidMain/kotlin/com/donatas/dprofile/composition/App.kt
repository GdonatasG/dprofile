package com.donatas.dprofile.composition

import android.app.Application
import com.donatas.dprofile.composition.di.commonModules
import com.donatas.dprofile.composition.di.delegateModule
import com.donatas.dprofile.composition.di.featureModule
import com.donatas.dprofile.composition.di.navigationModule
import com.donatas.dprofile.composition.navigation.ComposeModalFactory
import com.donatas.dprofile.composition.navigation.ComposeScreenFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.module.Module

actual open class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            this.allowOverride(true)
            androidContext(this@App)

            val modules = mutableListOf<Module>(
                navigationModule,


                // TODO: remove both after refactoring
                featureModule,
                delegateModule
            )

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
