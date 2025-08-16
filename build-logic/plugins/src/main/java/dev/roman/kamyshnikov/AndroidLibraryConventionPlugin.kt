package dev.roman.kamyshnikov

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                configureModulePlugins()
            }

            configureHilt()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                configureAndroidTestFixtures()
                defaultConfig.targetSdk = PluginConfig.TARGET_SDK
                defaultConfig.vectorDrawables.useSupportLibrary = true
            }

            addTestDependencies()
            addCoreDependencies()
            addNetworkDependencies()
        }
    }

    private fun Project.configureAndroidTestFixtures() {
        extensions.configure<LibraryExtension> {
            testFixtures.enable = true
        }

        dependencies {
            add("testFixturesImplementation", libs.findLibrary("androidx-compose-runtime").get())
            add("testFixturesCompileOnly", libs.findLibrary("kotlin-stdlib").get())
        }
    }
}
