package com.donatas.dprofile.composition.navigation.flow

import com.donatas.dprofile.alerts.popup.DefaultPopUpController
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.di.Scopes
import com.donatas.dprofile.composition.di.qualifier.PaginatorQualifier
import com.donatas.dprofile.composition.extensions.getOrCreateScope
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
import com.donatas.dprofile.composition.navigation.screens.EducationScreen
import com.donatas.dprofile.composition.navigation.screens.EducationScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ExperienceScreen
import com.donatas.dprofile.composition.navigation.screens.ExperienceScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubScreen
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import com.donatas.dprofile.composition.navigation.screens.RoadToProgrammingScreen
import com.donatas.dprofile.composition.navigation.screens.RoadToProgrammingScreenFactory
import com.donatas.dprofile.composition.navigation.screens.SkillsScreen
import com.donatas.dprofile.composition.navigation.screens.SkillsScreenFactory
import com.donatas.dprofile.composition.presentation.BottomTab
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeTab
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import com.donatas.dprofile.features.aboutme.education.EducationViewModel
import com.donatas.dprofile.features.aboutme.experience.ExperienceViewModel
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingViewModel
import com.donatas.dprofile.features.aboutme.skills.SkillsViewModel
import com.donatas.dprofile.features.contacts.ContactsDelegate
import com.donatas.dprofile.features.contacts.ContactsViewModel
import com.donatas.dprofile.features.github.GetRepositories
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
import org.koin.core.component.KoinScopeComponent
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
        getOrCreateScope<MainFlow>(scope = Scopes.BOTTOM_TAB)
    }

    fun start() {
        var currentTab: Screen = scope.get<AboutMeScreen>()

        val tabs: List<BottomTab> = listOf(
            BottomTab(type = BottomTab.Type.ABOUT_ME, factory = {
                scope.get<AboutMeScreen>()
            }),
            BottomTab(type = BottomTab.Type.GITHUB, factory = {
                scope.get<GithubScreen>()
            }),
            BottomTab(type = BottomTab.Type.CONTACTS, factory = {
                scope.get<ContactsScreen>()
            }),
        )

        val bottomTabController = BottomTabBarController(tabs = tabs, onFinished = {
//            scope.close()
        })

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
                tabController = get<BottomTabBarController>(),
                appTutorial = get<AppTutorial>()
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
            scope = this, factory = get<AboutMeScreenFactory>(), appTutorial = get<AppTutorial>()
        )
    }

    sharedViewModel {
        AboutMeViewModel(tabs = listOf(AboutMeTab(type = AboutMeTab.Type.EXPERIENCE, factory = {
            this.get<ExperienceScreen>()
        }), AboutMeTab(type = AboutMeTab.Type.EDUCATION, factory = {
            this.get<EducationScreen>()
        }), AboutMeTab(type = AboutMeTab.Type.SKILLS, factory = {
            this.get<SkillsScreen>()
        }), AboutMeTab(type = AboutMeTab.Type.ROAD_TO_PROGRAMMING, factory = {
            this.get<RoadToProgrammingScreen>()
        })
        )
        )
    }

    sharedViewModel<ExperienceViewModel> {
        ExperienceViewModel()
    }

    scoped<ExperienceScreen> {
        ExperienceScreen(
            scope = this, factory = get<ExperienceScreenFactory>()
        )
    }

    sharedViewModel<EducationViewModel> {
        EducationViewModel()
    }

    scoped<EducationScreen> {
        EducationScreen(
            scope = this, factory = get<EducationScreenFactory>()
        )
    }

    sharedViewModel<SkillsViewModel> {
        SkillsViewModel()
    }

    scoped<SkillsScreen> {
        SkillsScreen(
            scope = this, factory = get<SkillsScreenFactory>()
        )
    }

    sharedViewModel<RoadToProgrammingViewModel> {
        RoadToProgrammingViewModel()
    }

    scoped<RoadToProgrammingScreen> {
        RoadToProgrammingScreen(
            scope = this, factory = get<RoadToProgrammingScreenFactory>()
        )
    }
}

internal class GithubUserLogin(val value: String)

internal class DefaultGetRepositories(
    private val repositoryService: RepositoryService
) : GetRepositories {
    override suspend fun invoke(page: Int, perPage: Int, login: String): LoadingResult<Repository> {
        val result = repositoryService.getRepositories {
            this.page {
                this.page = page
                this.perPage = perPage
            }
            this.user(login)
        }

        return suspendCoroutine<LoadingResult<Repository>> { continuation ->
            result.onSuccess { success ->
                if (success.items.isEmpty()) {
                    continuation.resume(LoadingResult.Empty(title = "No results found"))

                    return@onSuccess
                }

                continuation.resume(
                    LoadingResult.Data(
                        data = success.items.map { it.toDomain() }, total = success.total
                    )
                )
            }.onFailure {
                continuation.resume(
                    LoadingResult.Error(
                        title = "Failed!", message = "Unable to load repositories, try again"
                    )
                )
            }
        }
    }
}

private fun RepositoryResponse.toDomain(): Repository = Repository(
    title = this.name, language = this.language, htmlUrl = this.htmlUrl
)

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
            scope = this, factory = get<GithubScreenFactory>(), appTutorial = get<AppTutorial>()
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

    scoped<GetRepositories> {
        DefaultGetRepositories(
            repositoryService = get<RepositoryService>()
        )
    }

    sharedViewModel {
        GithubViewModel(
            userLogin = "GdonatasG",
            getUser = get<GetUser>(),
            getRepositories = get<GetRepositories>(),
            popUpController = DefaultPopUpController(),
            delegate = get<GithubDelegate>()
        )
    }
}

private fun ScopeDSL.contactsScreenComponents() {
    scoped<ContactsScreen> {
        ContactsScreen(
            scope = this, factory = get<ContactsScreenFactory>(), appTutorial = get<AppTutorial>()
        )
    }

    scoped<ContactsDelegate> {
        DefaultContactsDelegate(
            navigator = get<Navigator>()
        )
    }

    sharedViewModel {
        ContactsViewModel(
            delegate = get<ContactsDelegate>()
        )
    }
}
