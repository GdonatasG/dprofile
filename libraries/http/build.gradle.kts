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
                with(Dependencies.Ktor) {
                    implementation(clientCore)
                    implementation(contentNegotiation)
                    implementation(serialization)
                }

                api(project(":libraries:logger"))
                implementation(project(":libraries:utils"))
            }
        }
        val commonTest by getting {
            dependencies {
                with(Dependencies.Ktor) {
                    implementation(clientMock)
                }

                implementation(kotlin("test"))
                implementation(project(":libraries:test"))
            }
        }
        val androidMain by getting {
            dependencies {
                with(Dependencies.Ktor) {
                    implementation(clientCIO)
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

            dependencies {
                with(Dependencies.Ktor) {
                    implementation(clientDarwin)
                }
            }
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
    namespace = "com.donatas.dprofile.http"
    compileSdk = Dependencies.Android.compileSDK
    defaultConfig {
        minSdk = Dependencies.Android.minSDK
    }
}
