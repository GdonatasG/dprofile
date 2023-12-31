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
                with(Dependencies.MultiplatformSettings) {
                    implementation(preferences)
                    implementation(preferencesNoArgs)
                }

                with(Dependencies.KotlinX) {
                    implementation(serializationCore)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":libraries:test"))
                implementation(kotlin("test"))

                with(Dependencies.KotlinX) {
                    implementation(serializationCore)
                }

                with(Dependencies.MultiplatformSettings) {
                    implementation(preferencesMock)
                }
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
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.donatas.dprofile.preferences"
    compileSdk = Dependencies.Android.compileSDK
    defaultConfig {
        minSdk = Dependencies.Android.minSDK
    }
}
