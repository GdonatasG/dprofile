package com.donatas.dprofile.composition.navigation.flow

import com.donatas.dprofile.composition.di.Scopes
import com.donatas.dprofile.composition.extensions.createScope
import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreen
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.composition.presentation.BottomTab
import com.donatas.dprofile.features.aboutme.AboutMeFeature
import com.donatas.dprofile.features.contacts.ContactsFeature
import com.donatas.dprofile.features.github.GithubFeature
import com.donatas.dprofile.utils.isDebug
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.get
import org.koin.core.scope.Scope
import org.koin.dsl.module

class MainFlow(
    private val navigator: Navigator
) : KoinScopeComponent {
    override val scope: Scope by lazy {
        createScope<MainFlow>(scope = Scopes.BOTTOM_TAB)
    }

    init {
        if (isDebug) {
            Napier.base(DebugAntilog())
        }
    }

    fun start() {
        val tabs: List<BottomTab> = listOf(
            BottomTab(
                type = BottomTab.Type.ABOUT_ME,
                factory = {
                    get<AboutMeFeature>().screen()
                }
            ),
            BottomTab(
                type = BottomTab.Type.GITHUB,
                factory = {
                    get<GithubFeature>().screen()
                }
            ),
            BottomTab(
                type = BottomTab.Type.CONTACTS,
                factory = {
                    get<ContactsFeature>().screen()
                }
            ),
        )

        val bottomTabController = BottomTabBarController(
            tabs = tabs,
            onFinished = {
                scope.close()
            }
        )

        scope.declare(bottomTabController)

        val screen = scope.get<BottomTabBarScreen>()

        navigator.set(screen)
    }

    companion object {
        val module = scope
    }
}

private val scope = module {
    scope<MainFlow> {
        scoped<BottomTabBarScreen> {
            BottomTabBarScreen(
                factory = get<BottomTabBarScreenFactory>(),
                tabController = get<BottomTabBarController>()
            )
        }
    }
}
