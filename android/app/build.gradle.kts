plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.donatas.dprofile"
    compileSdk = Dependencies.Android.compileSDK

    defaultConfig {
        applicationId = "com.donatas.dprofile"
        minSdk = Dependencies.Android.minSDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Dependencies.CompileOptions.sourceCompatibility
        targetCompatibility = Dependencies.CompileOptions.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Dependencies.KotlinOptions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

}

dependencies {
    implementation(project(":android:compose-components"))
    implementation(project(":composition"))

    implementation(Dependencies.Android.material)
}
