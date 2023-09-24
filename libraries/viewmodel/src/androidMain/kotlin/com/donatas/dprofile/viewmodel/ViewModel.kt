package com.donatas.dprofile.viewmodel

import androidx.lifecycle.viewModelScope

actual abstract class ViewModel : androidx.lifecycle.ViewModel() {
    actual val scope = viewModelScope

    init {
        println("Created: ${this.javaClass.simpleName}")
    }

    actual open fun onCreate() {}
    actual open fun onAppear() {}
    actual open fun onDisappear() {}
    actual open fun onClear() {}

    override fun onCleared() {
        super.onCleared()
        onClear()
        println("Cleared: ${this.javaClass.simpleName}")
    }
}
