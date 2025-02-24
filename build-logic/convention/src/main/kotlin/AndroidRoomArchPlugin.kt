import configuration.extensions.implementation
import configuration.extensions.ksp
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomArchPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("arch.android.library")
                apply("arch.android.hilt")
            }

            dependencies {
                implementation(libs.library("androidx_room"))
                ksp(libs.library("androidx_room_compiler"))
            }
        }
    }
}