package com.donatas.dprofile.features.aboutme.skills

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.skills.presentation.SkillsViewModel
import kotlinx.coroutines.cancelChildren
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import platform.UIKit.UIViewController
import swift.ui.*

actual class SkillsScreen actual constructor() : Screen, KoinComponent {
    private val factory = Factory()

    override fun controller(): UIViewController {
        val shared = get<SkillsViewModel>()
        val model = Model()

        @Suppress("CAST_NEVER_SUCCEEDS", "USELESS_CAST")
        val controller = factory.composeWithModel(model) as UIViewController

        return controller.onLifecycle(
            onAppear = {
                println("OPEN")
            },
            onDisappear = {
                println("CLOSE")
                shared.scope.coroutineContext.cancelChildren()
            }
        )
    }
}
