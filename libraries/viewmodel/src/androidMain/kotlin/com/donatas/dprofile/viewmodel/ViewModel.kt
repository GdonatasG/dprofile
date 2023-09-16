package com.donatas.dprofile.viewmodel

import androidx.lifecycle.viewModelScope

actual abstract class ViewModel : androidx.lifecycle.ViewModel() {
    actual val scope = viewModelScope

    actual open fun onCreate() {}
    actual open fun onAppear() {}
    actual open fun onDisappear() {}
}
