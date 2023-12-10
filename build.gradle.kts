buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.Gradle.plugin)
        classpath(Dependencies.Gradle.gradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version(Versions.gradle).apply(false)
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.android.application") version "7.4.2" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
