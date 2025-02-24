plugins {
    id("arch.android.room")
}

android {
    namespace = "com.core.data.db"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":core:domain"))
}