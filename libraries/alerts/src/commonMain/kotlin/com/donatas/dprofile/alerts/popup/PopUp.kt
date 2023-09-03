package com.donatas.dprofile.alerts.popup

class PopUp(
    configuration: PopUp.() -> Unit
) {
    var title: String = ""
    var message: String? = null
    var type: Type = Type.SUCCESS
    var onClick: (() -> Unit)? = null
    var dismissAfterInMillis: Long = 3000

    init {
        apply(configuration)
    }


    enum class Type { SUCCESS, ERROR }
}
