pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DProfile"

include(":android:compose-components")

include(":libraries:viewmodel")
include(":libraries:feature")
include(":libraries:alerts")
include(":libraries:coroutines")
include(":libraries:http")
include(":libraries:logger")
include(":libraries:preferences")
include(":libraries:test")
include(":libraries:utils")


//include(":features:template")
//include(":features:template:presentation")
//include(":features:template:ui:ios")
//include(":features:template:ui:android")
include(":composition")
include(":android:app")
