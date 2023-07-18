package com.donatas.dprofile.features.filter

import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.features.filter.presentation.FilterViewModel
import kotlinx.coroutines.cancelChildren
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import platform.UIKit.UIViewController
import swift.ui.*

actual class FilterModal actual constructor() : Modal, KoinComponent {
    private val factory = Factory()

    override fun controller(): UIViewController {
        val shared = get<FilterViewModel>()
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
