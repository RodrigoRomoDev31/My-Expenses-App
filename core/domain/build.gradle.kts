import configuration.extensions.implementation

plugins {
    id("arch.android.library")
}

android {
    namespace = "com.core.domain"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.androidx.room)
}