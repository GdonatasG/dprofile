plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

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
                with(Dependencies.KotlinX) {
                    api(project(":libraries:viewmodel"))
                }
            }
        }
        val androidMain by getting {
            dependencies {
                with(Dependencies.Android.Compose) {
                    api(platform(composeBom))
                    api(runtime)
                }
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
    namespace = "com.donatas.dprofile.feature"
    compileSdk = Dependencies.Android.compileSDK
    defaultConfig {
        minSdk = Dependencies.Android.minSDK
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}
