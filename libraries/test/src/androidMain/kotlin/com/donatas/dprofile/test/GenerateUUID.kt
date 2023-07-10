package com.donatas.dprofile.test

import java.util.UUID

actual fun generateUUID(): String = UUID.randomUUID().toString()
