package com.donatas.dprofile.composition.presentation.navigation

import androidx.navigation.NavOptions
import com.donatas.dprofile.feature.Modal
import com.ramcosta.composedestinations.spec.Direction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class AndroidAppNavigator(
    private val modalState: ModalState
) : AndroidNavigator {
    private val _navigateBackAction: MutableStateFlow<Unit?> by lazy {
        MutableStateFlow(null)
    }
    override val navigateBackAction: StateFlow<Unit?> get() = _navigateBackAction.asStateFlow()

    private val _navAction: MutableStateFlow<NavigationAction?> by lazy {
        MutableStateFlow(null)
    }
    override val navAction: StateFlow<NavigationAction?> get() = _navAction.asStateFlow()


    private val _modalAction: MutableStateFlow<Unit?> by lazy {
        MutableStateFlow(null)
    }
    override val modalAction: StateFlow<Unit?> get() = _modalAction.asStateFlow()

    override fun navigate(action: NavigationAction) {
        _navAction.update { action }
    }

    override fun showModal(screen: Modal) {
        modalState.setModal(screen)
        _modalAction.update { }
    }

    override fun navigateBack() {
        _navigateBackAction.update { }
    }

    override fun resetNavigationAction() {
        _navAction.value = null
    }

    override fun resetModalAction() {
        _modalAction.value = null
    }

    override fun resetBackAction() {
        _navigateBackAction.value = null
    }
}

internal interface AndroidNavigator {
    fun navigate(action: NavigationAction)
    fun showModal(screen: Modal)
    fun navigateBack()

    val navigateBackAction: StateFlow<Unit?>
    val navAction: StateFlow<NavigationAction?>
    val modalAction: StateFlow<Unit?>

    fun resetNavigationAction()
    fun resetModalAction()
    fun resetBackAction()
}

internal data class NavigationAction(
    val destination: Direction,
    val navOptions: AppNavOptions = AppNavOptions.Builder().build()
)

internal class AppNavOptions(
    val singleTop: Boolean,
    val popUpToRoute: String?,
    val popUpToInclusive: Boolean,
    val popUpAllInclusive: Boolean
) {
    internal fun toNavOptions(): NavOptions = NavOptions.Builder().apply {
        setLaunchSingleTop(singleTop = singleTop)
        setPopUpTo(route = popUpToRoute, inclusive = popUpToInclusive)
    }.build()

    internal class Builder {
        private var singleTop = false
        private var popUpToRoute: String? = null
        private var popUpToInclusive = false
        private var popUpAllInclusive = false

        internal fun setLaunchSingleTop(singleTop: Boolean): Builder {
            this.singleTop = singleTop

            return this
        }

        internal fun setPopUpTo(
            route: String?,
            inclusive: Boolean
        ): Builder {
            popUpAllInclusive = false
            popUpToRoute = route
            popUpToInclusive = inclusive

            return this
        }

        internal fun popUpAllInclusive(): Builder {
            popUpToRoute = null
            popUpToInclusive = false
            popUpAllInclusive = true

            return this
        }

        internal fun build(): AppNavOptions = AppNavOptions(
            singleTop = singleTop,
            popUpToRoute = popUpToRoute,
            popUpToInclusive = popUpToInclusive,
            popUpAllInclusive = popUpAllInclusive
        )
    }
}
