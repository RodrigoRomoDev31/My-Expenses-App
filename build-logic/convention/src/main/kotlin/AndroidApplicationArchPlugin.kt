import com.android.build.api.dsl.ApplicationExtension
import configuration.configureKotlinAndroid
import configuration.extensions.androidTestImplementation
import configuration.extensions.bundle
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.testImplementation
import configuration.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")

            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.version("targetSDK")
            }

            dependencies {
                implementation(libs.library("androidx-lifecycle-runtime-ktx"))
                testImplementation(libs.library("junit"))
                androidTestImplementation(libs.bundle("androidx-test"))
            }
        }
    }
}