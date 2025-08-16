@file:Suppress("StringLiteralDuplication", "MatchingDeclarationName")

package dev.roman.kamyshnikov

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal object PluginConfig {
    const val MIN_SDK = 26
    const val COMPILE_SDK = 35
    const val TARGET_SDK = 35
    const val BUILD_TOOLS_VERSION = "35.0.0"
    val javaVersion = JavaVersion.VERSION_17
    val jvmTarget = JvmTarget.JVM_17
}

internal fun PluginManager.configureModulePlugins() {
    apply("org.jetbrains.kotlin.android")
    apply("org.jetbrains.kotlin.plugin.serialization")
    apply("org.jetbrains.kotlin.plugin.parcelize")
    apply("com.google.devtools.ksp")
}

internal fun Project.configureHilt() {
    pluginManager.apply("dagger.hilt.android.plugin")

    with(this) {
        dependencies {
            add("implementation", libs.findLibrary("hilt-android").get())
            add("ksp", libs.findLibrary("hilt-compiler").get())
        }
    }
}

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = PluginConfig.COMPILE_SDK
        buildToolsVersion = PluginConfig.BUILD_TOOLS_VERSION

        defaultConfig {
            minSdk = PluginConfig.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = PluginConfig.javaVersion
            targetCompatibility = PluginConfig.javaVersion
            isCoreLibraryDesugaringEnabled = true
        }

        tasks.withType<KotlinCompile> {
            compilerOptions {
                allWarningsAsErrors.set(true) // Treat all Kotlin warnings as errors

                jvmTarget.set(PluginConfig.jvmTarget)
            }
        }

        buildFeatures {
            buildConfig = false
            aidl = false
            renderScript = false
            resValues = false
            shaders = false
            viewBinding = false
        }
    }

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android-desugarJdkLibs").get())
    }
}

internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("androidx-compose-runtime").get())
            add("implementation", libs.findLibrary("androidx-compose-ui").get())
            add("implementation", libs.findLibrary("androidx-compose-activity").get())
            add("implementation", libs.findLibrary("androidx-compose-ui-graphics").get())
            add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            add("implementation", libs.findLibrary("androidx-compose-material3").get())
            add("implementation", libs.findLibrary("kotlin-serialization").get())
            add("androidTestImplementation", platform(bom))
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-test-manifest").get())

        }
    }
}

internal fun Project.addTestDependencies() {
    dependencies {
        val junit5Bom = libs.findLibrary("test-junit5-bom").get()

        add("testImplementation", platform(junit5Bom))
        add("testImplementation", libs.findLibrary("test-coroutines").get())
        add("testImplementation", libs.findLibrary("test-junit-api").get())
        add("testImplementation", libs.findLibrary("test-junit-params").get())
        add("testImplementation", libs.findLibrary("test-mockk").get())
        add("testImplementation", libs.findLibrary("test-truth").get())
        add("testImplementation", libs.findLibrary("test-turbine").get())

        add("testRuntimeOnly", platform(junit5Bom))
        add("testRuntimeOnly", libs.findLibrary("test-junit-engine").get())
        add("testRuntimeOnly", libs.findLibrary("test-junit-platform-launcher").get())
    }
}

internal fun Project.addCoreDependencies() {
    dependencies {
        add("implementation", libs.findLibrary("kotlin-coroutines-core").get())
        add("implementation", libs.findLibrary("kotlin-coroutines-android").get())
        add("implementation", libs.findLibrary("arrow-core").get())
        add("implementation", libs.findLibrary("androidx-core-ktx").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
        add("implementation", libs.findLibrary("androidx-fragment-compose").get())
        add("implementation", libs.findLibrary("androidx-navigation-fragment").get())
        add("implementation", libs.findLibrary("androidx-navigation-ui").get())
        add("implementation", libs.findLibrary("androidx-navigation-ui").get())
    }
}

internal fun Project.addNetworkDependencies() {
    dependencies {
        add("implementation", libs.findLibrary("moshi-adapters").get())
        add("implementation", libs.findLibrary("moshi-codegen").get())
        add("implementation", libs.findLibrary("okhttp").get())
        add("implementation", libs.findLibrary("okhttp-logging").get())
        add("implementation", libs.findLibrary("okhttp-tls").get())
        add("implementation", libs.findLibrary("retrofit").get())
        add("implementation", libs.findLibrary("retrofit-moshi").get())
        add("implementation", libs.findLibrary("retrofit-scalars").get())
    }
}

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
