plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.donatas.dprofile.compose.components"
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
    implementation(Dependencies.Android.Compose.viewModel)
    implementation(Dependencies.Android.Compose.lottie)
    implementation(Dependencies.Koin.compose)
    implementation(Dependencies.Android.Compose.Destinations.core)
    implementation(Dependencies.Android.Compose.Destinations.animations)

    with(Dependencies.Android.Compose) {
        api(platform(composeBom))
        api(runtime)
        api(ui)
        api(foundation)
        api(animationAndroid)
        api(material)
        api(material3)
        api(materialIcons)
    }
}
