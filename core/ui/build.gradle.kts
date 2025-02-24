import configuration.extensions.implementation

plugins {
    id("arch.android.library")
    id("arch.android.library.compose")
}

android {
    namespace = "com.core.ui"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.lottie.compose)
    implementation(libs.coil.compose)
}