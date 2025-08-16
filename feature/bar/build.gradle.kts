plugins {
    id("convention.plugin.android.library")
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.core.network)
    implementation(projects.core.ui)
    testFixturesImplementation(projects.core.network)
}

android {
    namespace = "dev.roman.kamyshnikov.codegen.samples.feature.bar"
}
