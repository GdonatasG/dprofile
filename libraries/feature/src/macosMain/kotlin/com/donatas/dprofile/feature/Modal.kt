package com.donatas.dprofile.feature

import platform.AppKit.NSViewController

actual interface Modal {
    fun controller(): NSViewController
}
