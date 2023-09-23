plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.google.devtools.ksp") version Versions.ksp
}

group = "com.donatas.dprofile.composition"
version = "1.0.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.0"
        framework {
            baseName = "Dprofile"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Koin.core)

                implementation(project(":features:about-me"))
                implementation(project(":features:github"))
                implementation(project(":features:filter"))
                implementation(project(":features:contacts"))

                implementation(project(":libraries:feature"))
                implementation(project(":libraries:alerts"))
                implementation(project(":libraries:logger"))
                implementation(project(":libraries:http"))
                implementation(project(":libraries:preferences"))


                implementation(project(":github-services"))
                api(project(":libraries:utils"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":android:compose-components"))

                implementation(Dependencies.Android.splashScreen)
                implementation(Dependencies.Android.Accompanist.systemUiController)

                implementation(Dependencies.Android.Compose.Destinations.core)
                implementation(Dependencies.Android.Compose.Destinations.animations)

                implementation(Dependencies.Android.Compose.glide)

                implementation(Dependencies.Koin.compose)
                api(Dependencies.Koin.android)
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
    namespace = "com.donatas.dprofile.composition"
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

dependencies {
    add("kspAndroid", Dependencies.Android.Compose.Destinations.ksp)
}
