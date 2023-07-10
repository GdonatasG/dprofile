package com.donatas.dprofile.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

suspend fun <T> platformContext(
    block: suspend CoroutineScope.() -> T
): T = withContext(PlatformDispatcher, block)
