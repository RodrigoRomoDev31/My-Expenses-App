plugins {
    id("arch.android.library")
}

android {
    namespace = "com.core.store"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}