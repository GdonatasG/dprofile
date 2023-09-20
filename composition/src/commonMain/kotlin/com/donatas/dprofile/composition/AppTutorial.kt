package com.donatas.dprofile.composition

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppTutorial {
    private val totalSteps: Int = 9

    private val _state: MutableStateFlow<State> = MutableStateFlow(
        State(
            step = 1,
            isLastStep = false,
            isFinished = false
        )
    )
    val state: StateFlow<State> = _state.asStateFlow()

    fun previous(): Boolean {
        if (_state.value.step <= 1) return false

        val newStep = _state.value.step - 1

        _state.value = _state.value.copy(
            step = newStep,
            isLastStep = newStep == totalSteps,
            isFinished = false
        )

        return true
    }

    fun next() {
        if (_state.value.step >= totalSteps) return

        val newStep = _state.value.step + 1

        _state.value = _state.value.copy(
            step = newStep,
            isLastStep = newStep == totalSteps,
            isFinished = false
        )
    }

    fun setStepManually(step: Int) {
        _state.value = _state.value.copy(
            step = step,
            isLastStep = step == totalSteps,
            isFinished = false
        )
    }

    fun finish() {
        _state.value = _state.value.copy(
            isFinished = true
        )
    }

    data class State(
        val step: Int,
        val isLastStep: Boolean,
        val isFinished: Boolean
    )
}
