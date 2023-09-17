plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

val module = ":features:about-me:experience"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":libraries:viewmodel"))
            }
        }
        val androidMain by getting
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
    namespace = "com.donatas.dprofile.features.aboutme.experience"
    compileSdk = Dependencies.Android.compileSDK
    defaultConfig {
        minSdk = Dependencies.Android.minSDK
    }

    compileOptions {
        sourceCompatibility = Dependencies.CompileOptions.sourceCompatibility
        targetCompatibility = Dependencies.CompileOptions.targetCompatibility
    }
}
