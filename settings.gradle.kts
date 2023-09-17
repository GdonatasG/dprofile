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

include(":composition")
include(":android:app")
include(":android:compose-components")

include(":github-services")
include(":github-services:smoke-tests")

include(":libraries:viewmodel")
include(":libraries:feature")
include(":libraries:alerts")
include(":libraries:coroutines")
include(":libraries:http")
include(":libraries:logger")
include(":libraries:preferences")
include(":libraries:test")
include(":libraries:utils")
include(":libraries:loader")
include(":libraries:paginator")

include(":features:about-me")

include(":features:about-me:experience")
include(":features:about-me:experience:presentation")
include(":features:about-me:experience:ui:ios")
include(":features:about-me:experience:ui:android")

include(":features:about-me:education")
include(":features:about-me:education:presentation")
include(":features:about-me:education:ui:ios")
include(":features:about-me:education:ui:android")

include(":features:about-me:skills")
include(":features:about-me:skills:presentation")
include(":features:about-me:skills:ui:ios")
include(":features:about-me:skills:ui:android")

include(":features:about-me:road-to-programming")
include(":features:about-me:road-to-programming:presentation")
include(":features:about-me:road-to-programming:ui:ios")
include(":features:about-me:road-to-programming:ui:android")

include(":features:github")
include(":features:github:shared")

include(":features:github:search")
include(":features:github:search:presentation")
include(":features:github:search:ui:ios")
include(":features:github:search:ui:android")

include(":features:filter")
include(":features:filter:presentation")
include(":features:filter:ui:ios")
include(":features:filter:ui:android")

include(":features:filter:shared")

include(":features:contacts")
