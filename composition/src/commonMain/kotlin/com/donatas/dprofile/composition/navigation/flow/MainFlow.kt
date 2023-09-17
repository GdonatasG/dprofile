package com.donatas.dprofile.composition.navigation.flow

import com.donatas.dprofile.alerts.popup.DefaultPopUpController
import com.donatas.dprofile.composition.di.Scopes
import com.donatas.dprofile.composition.di.qualifier.PaginatorQualifier
import com.donatas.dprofile.composition.extensions.createScope
import com.donatas.dprofile.composition.extensions.sharedViewModel
import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.composition.navigation.delegate.DefaultContactsDelegate
import com.donatas.dprofile.composition.navigation.delegate.DefaultGithubDelegate
import com.donatas.dprofile.composition.navigation.screens.AboutMeScreen
import com.donatas.dprofile.composition.navigation.screens.AboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreen
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ContactsScreen
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubScreen
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.composition.presentation.BottomTab
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import com.donatas.dprofile.features.contacts.ContactsDelegate
import com.donatas.dprofile.features.contacts.ContactsViewModel
import com.donatas.dprofile.features.github.GetUser
import com.donatas.dprofile.features.github.GithubDelegate
import com.donatas.dprofile.features.github.GithubUser
import com.donatas.dprofile.features.github.GithubViewModel
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.githubservices.repository.RepositoryResponse
import com.donatas.dprofile.githubservices.repository.RepositoryService
import com.donatas.dprofile.githubservices.user.UserService
import com.donatas.dprofile.loader.LoadingResult
import com.donatas.dprofile.paginator.DefaultPaginator
import com.donatas.dprofile.paginator.Paginator
import com.donatas.dprofile.paginator.PerPage
import com.donatas.dprofile.utils.isDebug
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.ScopeDSL
import org.koin.dsl.module
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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
        var currentTab: Screen = scope.get<AboutMeScreen>()

        val tabs: List<BottomTab> = listOf(
            BottomTab(
                type = BottomTab.Type.ABOUT_ME,
                factory = {
                    if (currentTab is AboutMeScreen) {
                        currentTab
                    } else {
                        currentTab = scope.get<AboutMeScreen>()
                        currentTab
                    }
                }
            ),
            BottomTab(
                type = BottomTab.Type.GITHUB,
                factory = {
                    if (currentTab is GithubScreen) {
                        currentTab
                    } else {
                        currentTab = scope.get<GithubScreen>()
                        currentTab
                    }
                }
            ),
            BottomTab(
                type = BottomTab.Type.CONTACTS,
                factory = {
                    if (currentTab is ContactsScreen) {
                        currentTab
                    } else {
                        currentTab = scope.get<ContactsScreen>()
                        currentTab
                    }
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

        aboutMeScreenComponents()
        githubScreenComponents()
        contactsScreenComponents()
    }
}

private fun ScopeDSL.aboutMeScreenComponents() {
    scoped<AboutMeScreen> {
        AboutMeScreen(
            factory = get<AboutMeScreenFactory>(),
            viewModel = get<AboutMeViewModel>()
        )
    }

    scoped {
        AboutMeViewModel()
    }
}

internal class GithubUserLogin(val value: String)

internal class DefaultGetRepositoriesUseCase(
    private val githubUserLogin: GithubUserLogin,
    private val repositoryService: RepositoryService
) : GetRepositories {
    override suspend fun invoke(page: Int, perPage: Int): LoadingResult<Repository> {
        val result = repositoryService.getRepositories {
            this.page {
                this.page = page
                this.perPage = perPage
            }
            this.user(githubUserLogin.value)
        }

        return suspendCoroutine<LoadingResult<Repository>> { continuation ->
            result.onSuccess { success ->
                if (success.items.isEmpty()) {
                    continuation.resume(LoadingResult.Empty(title = "No results found"))

                    return@onSuccess
                }

                continuation.resume(
                    LoadingResult.Data(
                        data = success.items.map { it.toDomain() },
                        total = success.total
                    )
                )
            }.onFailure {
                continuation.resume(
                    LoadingResult.Error(
                        title = "Failed!",
                        message = "Unable to load repositories, try again"
                    )
                )
            }
        }
    }
}

private fun RepositoryResponse.toDomain(): Repository = Repository(
    title = this.name,
    language = this.language,
    htmlUrl = this.htmlUrl
)


internal interface GetRepositories {
    suspend operator fun invoke(page: Int, perPage: Int): LoadingResult<Repository>
}

internal class DefaultGetUserUseCase(
    private val userService: UserService
) : GetUser {
    override suspend fun invoke(user: String): Result<GithubUser> {
        return userService.getUser(user).map {
            GithubUser(
                login = it.login,
                location = it.location,
                followers = it.followers,
                following = it.following,
                avatarUrl = it.avatarUrl,
                htmlUrl = it.htmlUrl
            )
        }
    }

}

private fun ScopeDSL.githubScreenComponents() {
    scoped<GithubScreen> {
        GithubScreen(
            factory = get<GithubScreenFactory>(),
            viewModel = get<GithubViewModel>()
        )
    }

    scoped { GithubUserLogin(value = "GdonatasG") }

    scoped<GetRepositories> {
        DefaultGetRepositoriesUseCase(
            githubUserLogin = get<GithubUserLogin>(),
            repositoryService = get<RepositoryService>()
        )
    }

    scoped<GetUser> {
        DefaultGetUserUseCase(
            userService = get<UserService>()
        )
    }

    scoped<GithubDelegate> {
        DefaultGithubDelegate(
            navigator = get<Navigator>()
        )
    }

    scoped<Paginator<Repository>>(qualifier = named(PaginatorQualifier.GITHUB)) {
        val getRepositories: GetRepositories = get<GetRepositories>()

        DefaultPaginator<Repository>(
            perPage = PerPage(30),
            onLoad = { page, perPage ->
                getRepositories(
                    page = page.value,
                    perPage = perPage.value
                )
            }
        )
    }

    scoped {
        GithubViewModel(
            githubUserLogin = get<GithubUserLogin>().value,
            paginator = get<Paginator<Repository>>(qualifier = named(PaginatorQualifier.GITHUB)),
            getUser = get<GetUser>(),
            popUpController = DefaultPopUpController(),
            delegate = get<GithubDelegate>()
        )
    }
}

private fun ScopeDSL.contactsScreenComponents() {
    scoped<ContactsScreen> {
        ContactsScreen(
            factory = get<ContactsScreenFactory>(),
            viewModel = get<ContactsViewModel>()
        )
    }

    scoped<ContactsDelegate> {
        DefaultContactsDelegate(
            navigator = get<Navigator>()
        )
    }

    scoped {
        ContactsViewModel(
            delegate = get<ContactsDelegate>()
        )
    }
}
