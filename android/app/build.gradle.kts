plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.donatas.dprofile"
    compileSdk = Dependencies.Android.compileSDK

    defaultConfig {
        applicationId = "com.donatas.dprofile"
        minSdk = Dependencies.Android.minSDK
        targetSdk = Dependencies.Android.targetSDK
        versionCode = 2
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            this.isMinifyEnabled = true
            this.isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

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
    implementation(platform(Dependencies.Android.Firebase.firebaseBom))
    implementation(Dependencies.Android.Firebase.analytics)
    implementation(Dependencies.Android.Firebase.crashlytics)
    implementation(project(":android:compose-components"))
    implementation(project(":composition"))

    implementation(Dependencies.Android.material)
}
