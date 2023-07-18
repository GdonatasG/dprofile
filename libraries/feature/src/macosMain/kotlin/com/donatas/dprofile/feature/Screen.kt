package com.donatas.dprofile.feature
import platform.AppKit.NSViewController

actual interface Screen {
    fun controller(): NSViewController
}
