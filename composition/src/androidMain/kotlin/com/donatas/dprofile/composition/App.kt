package com.donatas.dprofile.composition

import android.app.Application
import com.donatas.dprofile.composition.di.flowModule
import com.donatas.dprofile.composition.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

actual open class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            this.allowOverride(true)
            androidContext(this@App)
            modules(
                navigationModule
            )
            modules(
                flowModule
            )
        }
    }
}
