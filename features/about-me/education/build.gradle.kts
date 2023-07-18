plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

val module = ":features:about-me:education"

kotlin {
    android()

    val config: org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.(String) -> Unit = Config@{
        if (org.jetbrains.kotlin.cli.common.isWindows) return@Config

        val platform = when (name) {
            "iosX64", "iosSimulatorArm64" -> "iphonesimulator"
            "iosArm64" -> "iphoneos"
            else -> error("Unsupported target: $name")
        }

        compilations.getByName("main") {
            cinterops.create("UI") {
                val interopTask = tasks[interopProcessingTaskName]
                interopTask.dependsOn("$module:ui:ios:build${platform.capitalize()}")
                includeDirs.headerFilterOnly("$projectDir/ui/ios/build/Release-$platform/include")
            }
        }
    }

    iosX64 {
        config(name)
    }

    iosArm64 {
        config(name)
    }

    iosSimulatorArm64 {
        config(name)
    }

    macosX64()
    macosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":libraries:feature"))
                implementation(project(":libraries:viewmodel"))
                api(project("$module:presentation"))
                implementation(Dependencies.KotlinX.coroutinesCore)
                implementation(Dependencies.Koin.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project("$module:ui:android"))
                implementation(project(":android:compose-components"))
                implementation(Dependencies.Koin.android)
                implementation(Dependencies.Koin.compose)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val macosX64Main by getting
        val macosArm64Main by getting
        val macosMain by creating {
            dependsOn(commonMain)
            macosX64Main.dependsOn(this)
            macosArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.donatas.dprofile.features.aboutme.education"
    compileSdk = Dependencies.Android.compileSDK
    defaultConfig {
        minSdk = Dependencies.Android.minSDK
    }

    compileOptions {
        sourceCompatibility = Dependencies.CompileOptions.sourceCompatibility
        targetCompatibility = Dependencies.CompileOptions.targetCompatibility
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}
