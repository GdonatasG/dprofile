package com.donatas.dprofile.viewmodel

import androidx.lifecycle.viewModelScope

actual abstract class ViewModel: androidx.lifecycle.ViewModel() {
    actual val scope = viewModelScope
}
