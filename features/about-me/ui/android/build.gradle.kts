plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.donatas.dprofile.features.aboutme.ui"
    compileSdk = Dependencies.Android.compileSDK

    defaultConfig {
        minSdk = Dependencies.Android.minSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    kotlinOptions {
        jvmTarget = Dependencies.KotlinOptions.jvmTarget
    }
}

dependencies {
    implementation(project(":features:about-me:presentation"))

    implementation(project(":libraries:utils"))

    with(Dependencies.Android) {
        implementation(coreKtx)
        implementation(appCompat)
        implementation(material)
    }

    implementation(project(":android:compose-components"))
}
