plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.donatas.dprofile"
    compileSdk = Dependencies.Android.compileSDK

    signingConfigs {
        create("release") {
            storeFile = file("key.keystore")
            storePassword = "test123"
            keyAlias = "projectspace"
            keyPassword = "test123"
        }
    }
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
            this.signingConfig = signingConfigs.getByName("release")
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
    implementation(platform(Dependencies.Android.Firebase.firebaseBom))
    implementation(Dependencies.Android.Firebase.analytics)
    implementation(Dependencies.Android.Firebase.crashlytics)
    implementation(project(":android:compose-components"))
    implementation(project(":composition"))

    implementation(Dependencies.Android.material)
}
