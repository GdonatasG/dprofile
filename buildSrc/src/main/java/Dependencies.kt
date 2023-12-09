import org.gradle.api.JavaVersion
import org.gradle.internal.jvm.Jvm

object Versions {
    const val kotlin = "1.8.0"
    const val coroutines = "1.6.4"
    const val ktor = "2.1.3"
    const val multiplatformSettings = "0.9"
    const val kotlinxSerialization = "1.3.3"
    const val composeCompiler = "1.4.1"
    const val composeDestinations = "1.9.42-beta"
    const val ksp = "1.8.0-1.0.9"
    const val koin = "3.2.2"
    const val lottieCompose = "5.2.0"
    const val accompanist = "0.31.3-beta"
    const val napier = "2.6.1"
    const val gradle = "7.4.2"
}

object Dependencies {
    object Gradle {
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    }

    object Android {
        const val minSDK = 26
        const val compileSDK = 34
        const val maxSDK = 33

        object Accompanist {
            const val flowLayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
            const val systemUiController =
                "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
        }

        object Firebase {
            const val firebaseBom = "com.google.firebase:firebase-bom:32.7.0"
            const val crashlytics = "com.google.firebase:firebase-crashlytics"
            const val analytics = "com.google.firebase:firebase-analytics"
        }

        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val material = "com.google.android.material:material:1.9.0"
        const val splashScreen = "androidx.core:core-splashscreen:1.0.1"

        const val testExtJunit = "androidx.test.ext:junit:1.1.5"
        const val testEspressoCore = "androidx.test.espresso:espresso-core:3.5.1"
        const val tesUIAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"

        object Compose {
            val composeBom = "androidx.compose:compose-bom:2023.06.01"
            const val runtime = "androidx.compose.runtime:runtime"
            const val ui = "androidx.compose.ui:ui"
            const val foundation = "androidx.compose.foundation:foundation"
            const val material = "androidx.compose.material:material"
            const val material3 = "androidx.compose.material3:material3"
            const val materialIcons = "androidx.compose.material:material-icons-extended"
            const val activity = "androidx.activity:activity-compose:1.6.1"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:1.3.3"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
            const val animationAndroid = "androidx.compose.animation:animation-android:1.5.0-beta01"
            const val lottie = "com.airbnb.android:lottie-compose:${Versions.lottieCompose}"
            const val glide = "com.github.bumptech.glide:compose:1.0.0-alpha.5"

            object Destinations {
                const val core = "io.github.raamcosta.compose-destinations:core:${Versions.composeDestinations}"
                const val animations =
                    "io.github.raamcosta.compose-destinations:animations-core:${Versions.composeDestinations}"
                const val ksp = "io.github.raamcosta.compose-destinations:ksp:${Versions.composeDestinations}"
            }
        }
    }

    object CompileOptions {
        val sourceCompatibility = JavaVersion.VERSION_11
        val targetCompatibility = JavaVersion.VERSION_11
    }

    object KotlinOptions {
        val jvmTarget = "11"
    }

    object Ktor {
        const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val clientDarwin = "io.ktor:ktor-client-darwin:${Versions.ktor}"
        const val clientCIO = "io.ktor:ktor-client-cio:${Versions.ktor}"
        const val clientMock = "io.ktor:ktor-client-mock:${Versions.ktor}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val testJUnit4 = "io.insert-koin:koin-test-junit4:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object KotlinX {
        const val serializationCore =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object MultiplatformSettings {
        const val preferences = "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
        const val preferencesNoArgs = "com.russhwolf:multiplatform-settings-no-arg:${Versions.multiplatformSettings}"
        const val preferencesMock = "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"
    }

    object Logging {
        const val napier = "io.github.aakira:napier:${Versions.napier}"
    }
}
