package configuration

import com.android.build.api.dsl.CommonExtension
import configuration.extensions.androidTestImplementation
import configuration.extensions.bundle
import configuration.extensions.debugImplementation
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.library("androidx-compose-bom")
            implementation(platform(bom))
            implementation(libs.bundle("androidx-compose"))
            implementation(libs.bundle("androidx-lifecycle-compose"))
            debugImplementation(libs.bundle("androidx-compose-test"))
            androidTestImplementation(platform(bom))
            androidTestImplementation(libs.library("androidx-ui-test-junit4"))
        }
    }
}