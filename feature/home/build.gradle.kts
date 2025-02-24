plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.romvaz.feature.home"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.acompanist.permissions)
    implementation(libs.lottie.compose)
    implementation(libs.ycharts)
}
