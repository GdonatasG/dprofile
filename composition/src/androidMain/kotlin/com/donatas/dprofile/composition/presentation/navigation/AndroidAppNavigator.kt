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

    private val _navAction: MutableStateFlow<NavigationAction?> by lazy {
        MutableStateFlow(null)
    }

    private val _modalAction: MutableStateFlow<Unit?> by lazy {
        MutableStateFlow(null)
    }

    private val _closeModal: MutableStateFlow<Unit?> by lazy {
        MutableStateFlow(null)
    }

    private val _urlNavActions: MutableStateFlow<String?> by lazy {
        MutableStateFlow(null)
    }

    override val closeModal: StateFlow<Unit?> get() = _closeModal.asStateFlow()

    override val urlNavActions: StateFlow<String?> get() = _urlNavActions.asStateFlow()

    override val navigateBackAction: StateFlow<Unit?> get() = _navigateBackAction.asStateFlow()
    override val modalAction: StateFlow<Unit?> get() = _modalAction.asStateFlow()
    override val navAction: StateFlow<NavigationAction?> get() = _navAction.asStateFlow()


    override fun navigate(action: NavigationAction) {
        _navAction.update { action }
    }

    override fun navigateBack() {
        _navigateBackAction.update { }
    }

    override fun showModal(screen: Modal) {
        modalState.setModal(screen)
        _modalAction.update { }
    }

    override fun closeModal() {
        _closeModal.update { }
    }

    override fun navigateToUrl(url: String) {
        _urlNavActions.update { url }
    }

    override fun resetBackAction() {
        _navigateBackAction.value = null
    }

    override fun resetNavigationAction() {
        _navAction.value = null
    }

    override fun resetNavigateToUrlAction() {
        _urlNavActions.value = null
    }

    override fun resetModalAction() {
        _modalAction.value = null
        _closeModal.value = null
    }
}

internal interface AndroidNavigator {
    fun navigate(action: NavigationAction)
    fun navigateBack()
    fun navigateToUrl(url: String)
    fun showModal(screen: Modal)
    fun closeModal()

    val navigateBackAction: StateFlow<Unit?>
    val navAction: StateFlow<NavigationAction?>
    val modalAction: StateFlow<Unit?>
    val closeModal: StateFlow<Unit?>
    val urlNavActions: StateFlow<String?>

    fun resetBackAction()
    fun resetNavigationAction()
    fun resetNavigateToUrlAction()
    fun resetModalAction()
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
