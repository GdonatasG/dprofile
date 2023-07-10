package com.donatas.dprofile.utils

import com.donatas.dprofile.coroutines.platformContext
import kotlinx.coroutines.*

class PlatformScopeManager {
    private val scope: CoroutineScope by lazy {
        MainScope() + SupervisorJob()
    }

    private var disposeBag = mutableSetOf<Job>()

    fun cancelAllJobs() {
        disposeBag.forEach {
            it.cancel()
        }
    }

    fun launch(block: suspend () -> Any?) {
        disposeBag += scope.launch {
            platformContext {
                block()
            }
        }
    }
}
