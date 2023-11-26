package com.donatas.dprofile.composition

import com.donatas.dprofile.preferences.Preferences
import com.donatas.dprofile.preferences.getObject
import com.donatas.dprofile.preferences.setObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppTutorial(
    private val preferences: Preferences
) {
    private val totalSteps: Int = 9

    private val _state: MutableStateFlow<State> = MutableStateFlow(
        State(
            step = 1,
            isLastStep = false,
            isFinished = false,
            isStarted = false
        )
    )
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        val savedStep: Int = preferences.getObject<Int>(KEY_STEP) ?: 1
        val isFinished: Boolean = preferences.getObject<Boolean>(KEY_IS_FINISHED) ?: false
        val isStarted: Boolean = preferences.getObject<Boolean>(KEY_IS_STARTED) ?: false

        _state.value = State(
            step = savedStep,
            isLastStep = savedStep == totalSteps,
            isFinished = isFinished,
            isStarted = isStarted
        )
    }

    fun previous(): Boolean {
        if (_state.value.step <= 1) return false

        val newStep = _state.value.step - 1

        preferences.setObject<Int>(KEY_STEP, newStep)

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

        if (newStep == 2) {
            preferences.setObject<Boolean>(KEY_IS_STARTED, true)
        }

        preferences.setObject<Int>(KEY_STEP, newStep)

        _state.value = _state.value.copy(
            step = newStep,
            isLastStep = newStep == totalSteps,
            isFinished = false,
            isStarted = preferences.getObject<Boolean>(KEY_IS_STARTED) ?: false
        )
    }

    fun setStepManually(step: Int) {
        preferences.setObject<Int>(KEY_STEP, step)

        _state.value = _state.value.copy(
            step = step,
            isLastStep = step == totalSteps,
            isFinished = false
        )
    }

    fun finish() {
        preferences.setObject<Boolean>(KEY_IS_FINISHED, true)

        _state.value = _state.value.copy(
            isFinished = true
        )
    }

    data class State(
        val step: Int,
        val isLastStep: Boolean,
        val isFinished: Boolean,
        val isStarted: Boolean
    )

    companion object {
        private const val KEY_STEP = "STEP"
        private const val KEY_IS_FINISHED = "IS_FINISHED"
        private const val KEY_IS_STARTED = "IS_STARTED"
    }
}
