package com.donatas.dprofile.utils

import java.util.UUID

actual fun generateUUID(): String = UUID.randomUUID().toString()
