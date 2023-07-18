package com.donatas.dprofile.viewmodel

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus

actual abstract class ViewModel {
    actual val scope = MainScope() + SupervisorJob()
}
