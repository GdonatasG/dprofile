package com.donatas.dprofile.feature

import platform.UIKit.UIViewController

actual interface Screen {
    fun controller(): UIViewController
}
