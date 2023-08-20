package com.donatas.dprofile.utils

import platform.Foundation.NSUUID

actual fun generateUUID(): String = NSUUID().UUIDString()
