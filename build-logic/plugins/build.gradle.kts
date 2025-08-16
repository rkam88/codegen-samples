plugins {
    `kotlin-dsl`
}

group = "dev.roman.kamyshnikov.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.serialization.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "convention.plugin.android.library"
            implementationClass = "dev.roman.kamyshnikov.AndroidLibraryConventionPlugin"
        }
        register("androidApplication") {
            id = "convention.plugin.android.application"
            implementationClass = "dev.roman.kamyshnikov.AndroidApplicationConventionPlugin"
        }
    }
}
