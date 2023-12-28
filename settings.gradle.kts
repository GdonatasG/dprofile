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

rootProject.name = "dprofile"

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
include(":features:about-me:education")
include(":features:about-me:skills")
include(":features:about-me:road-to-programming")

include(":features:github")
include(":features:github:shared")
include(":features:github:search")

include(":features:filter")
include(":features:filter:shared")

include(":features:contacts")
