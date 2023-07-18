package com.donatas.dprofile.composition

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import platform.UIKit.UIScene
import platform.UIKit.UIWindow
import platform.UIKit.UIWindowScene

actual class App : KoinComponent {
    private lateinit var window: UIWindow

    fun start(scene: UIScene) {
        scene as UIWindowScene

        setupKoin()
        val controller = get<UINavigationControllerWrapper>()

        val flow = get<RootFlow>()
        flow.start()

        val window = UIWindow(windowScene = scene)
        window.setRootViewController(controller.shared)
        this.window = window
        window.makeKeyAndVisible()
    }

    private fun setupKoin() {
        startKoin {
            modules()
        }
    }
}
