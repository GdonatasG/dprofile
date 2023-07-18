package com.donatas.dprofile.http.utils

import kotlinx.serialization.json.JsonBuilder

fun JsonBuilder.applyRequired() {
    ignoreUnknownKeys = true
    encodeDefaults = true
    isLenient = true
    coerceInputValues = true
}
