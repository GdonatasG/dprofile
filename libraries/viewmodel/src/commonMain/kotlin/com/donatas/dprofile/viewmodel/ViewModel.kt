package com.donatas.dprofile.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

expect abstract class ViewModel() {
    val scope: CoroutineScope
}

fun <R, T : StateFlow<R>> ViewModel.bindStateFlow(state: T, binding: (R) -> Unit) {
    scope.launch {
        state.collect(binding)
    }
}
