package com.donatas.dprofile.composition.extensions

import com.donatas.dprofile.composition.di.Scopes
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.ScopeID

internal inline fun <reified T : Any> KoinScopeComponent.createScope(scopeId: ScopeID) =
    getKoin().createScope<T>(scopeId = scopeId)

internal inline fun <reified T: Any> KoinScopeComponent.createScope(scope: Scopes) = getKoin().createScope<T>(scopeId = scope.name)
