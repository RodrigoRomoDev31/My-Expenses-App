plugins {
    id("arch.android.library")
    id("arch.android.hilt")
}

android {
    namespace = "com.core.data"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data:room"))
}