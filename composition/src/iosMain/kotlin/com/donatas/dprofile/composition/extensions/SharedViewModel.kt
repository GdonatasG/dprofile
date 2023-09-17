package com.donatas.dprofile.composition.extensions

import com.donatas.dprofile.viewmodel.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.ScopeDSL

actual inline fun <reified T : ViewModel> Module.sharedViewModel(
    qualifier: Qualifier?,
    noinline definition: Definition<T>
): Pair<Module, InstanceFactory<T>> = factory(qualifier = qualifier, definition = definition)

actual inline fun <reified T : ViewModel> ScopeDSL.sharedViewModel(
    qualifier: Qualifier?,
    noinline definition: Definition<T>
): KoinDefinition<T> = factory(qualifier = qualifier, definition = definition)
