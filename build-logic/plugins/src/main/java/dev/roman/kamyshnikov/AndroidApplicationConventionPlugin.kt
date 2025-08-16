package dev.roman.kamyshnikov

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                configureModulePlugins()
            }

            configureHilt()

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                defaultConfig.targetSdk = PluginConfig.TARGET_SDK
                defaultConfig.vectorDrawables.useSupportLibrary = true
            }

            addTestDependencies()
            addCoreDependencies()
            addNetworkDependencies()
        }
    }
}
