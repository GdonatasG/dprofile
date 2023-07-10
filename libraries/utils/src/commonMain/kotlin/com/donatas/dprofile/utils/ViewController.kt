package com.donatas.dprofile.utils

abstract class ViewController<View> {
    open fun setView(view: View) {}
    open fun dropView() {}
}
