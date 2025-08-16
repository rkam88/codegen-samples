plugins {
    id("convention.plugin.android.application")
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.core.network)
    implementation(projects.core.ui)
}

android {
    namespace = "dev.roman.kamyshnikov.codegen.samples"

    defaultConfig {
        applicationId = "dev.roman.kamyshnikov.codegen.samples"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
