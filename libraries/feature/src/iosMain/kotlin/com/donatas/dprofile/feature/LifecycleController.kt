package com.donatas.dprofile.feature

import kotlinx.cinterop.ObjCObjectBase
import platform.Foundation.NSCoder
import platform.UIKit.*

fun UIViewController.onLifecycke(
    onAppear: () -> Unit = {},
    onDisappear: () -> Unit = {}
): UIViewController {
    val controller = LifecycleController()
    controller.onAppear = onAppear
    controller.onDisappear = onDisappear
    return controller.add(this)
}

private fun UIViewController.add(child: UIViewController): UIViewController {
    addChildViewController(child)
    view.addSubview(child.view)
    child.view.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activateConstraints(
        listOf(
            child.view.heightAnchor.constraintEqualToAnchor(view.heightAnchor),
            child.view.widthAnchor.constraintEqualToAnchor(view.widthAnchor),
            child.view.centerXAnchor.constraintEqualToAnchor(view.centerXAnchor),
            child.view.centerYAnchor.constraintEqualToAnchor(view.centerYAnchor)
        )
    )
    child.didMoveToParentViewController(this)
    return this
}

class LifecycleController: UIViewController {
    var onAppear: () -> Unit = {}
    var onDisappear: () -> Unit = {}

    @OverrideInit
    constructor(): super(nibName = null, bundle = null)

    @OverrideInit
    constructor(coder: NSCoder): super(coder)

    override fun viewDidAppear(animated: Boolean) {
        super.viewDidAppear(animated)
        onAppear()
    }

    override fun viewDidDisappear(animated: Boolean) {
        super.viewDidDisappear(animated)
        onDisappear()
    }
}
