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

include(":composition")
include(":android:app")

include(":libraries:viewmodel")
include(":libraries:feature")
include(":libraries:alerts")
include(":libraries:coroutines")
include(":libraries:http")
include(":libraries:logger")
include(":libraries:preferences")
include(":libraries:test")
include(":libraries:utils")

include(":features:about-me")
include(":features:about-me:presentation")
include(":features:about-me:ui:ios")
include(":features:about-me:ui:android")

include(":features:about-me:experience")
include(":features:about-me:experience:presentation")
include(":features:about-me:experience:ui:ios")
include(":features:about-me:experience:ui:android")


//include(":features:template")
//include(":features:template:presentation")
//include(":features:template:ui:ios")
//include(":features:template:ui:android")
