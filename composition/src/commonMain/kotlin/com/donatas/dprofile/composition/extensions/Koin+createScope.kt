package com.donatas.dprofile.composition.extensions

import org.koin.core.Koin
import org.koin.core.component.KoinScopeComponent
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

inline fun <reified T : Any> KoinScopeComponent.createScope(qualifier: Qualifier): Scope {
    return getKoin().createScope<T>(qualifier)
}

inline fun <reified T : Any> Koin.createScope(qualifier: Qualifier): Scope {
    return createScope<T>(scopeId = qualifier.value)
}
