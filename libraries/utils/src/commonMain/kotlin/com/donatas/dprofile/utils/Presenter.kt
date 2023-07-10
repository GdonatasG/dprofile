package com.donatas.dprofile.utils

abstract class Presenter<View : Any> : ViewDependencyMixin<View>() {
    open fun onResume() {}
    open fun onAppear() {}
    open fun onDisappear() {}
}
