package com.donatas.dprofile.composition.extensions

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.di.Scopes
import org.koin.androidx.compose.getKoin
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeID

@Composable
fun getScope(scopeId: ScopeID): Scope = getKoin().getScope(scopeId = scopeId)

@Composable
fun getScope(scope: Scopes): Scope = getKoin().getScope(scopeId = scope.name)
