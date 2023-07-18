package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.delegate.DefaultGithubDelegate
import com.donatas.dprofile.features.github.presentation.GithubDelegate
import org.koin.core.module.Module
import org.koin.dsl.module

internal val delegateModule: Module = module {
    single<GithubDelegate>() {
        DefaultGithubDelegate()
    }
}
