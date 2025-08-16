pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "codegen-samples"
include(":app")

// Automatically include all modules from the core and feature folders
rootDir.getSubFolders()
    .filter { it.name == "core" || it.name == "feature" }
    .flatMap { it.getSubFolders() }
    .onEach { if (it.getSubFolders().isEmpty()) createModuleFiles(it) }
    .forEach { include(":${it.parentFile.name}:${it.name}") }

private fun File.getSubFolders(): List<File> {
    return this.listFiles()?.filter { it.isDirectory && it.isHidden.not() } ?: emptyList()
}

private fun createModuleFiles(module: File) {
    val parentPackage = module.parentFile.name
    val moduleName = module.name
    val appPackages = listOf("dev", "roman", "kamyshnikov", "codegen", "samples", parentPackage, moduleName)

    // Create folder structure
    listOf("main", "test", "testFixtures")
        .map { "src/$it/kotlin/${appPackages.joinToString("/")}" }
        .map { targetPath -> File(module, targetPath) }
        .forEach { newDir -> newDir.mkdirs() }

    // Create build.gradle.kts
    File(module, "build.gradle.kts")
        .takeIf { it.exists().not() }
        ?.apply {
            createNewFile()
            writeText(
                """
                |plugins {
                |    id("convention.plugin.android.library")
                |}
                |
                |dependencies {
                |    implementation(projects.core.navigation)
                |    implementation(projects.core.network)
                |    implementation(projects.core.ui)
                |    testFixturesImplementation(projects.core.network)
                |}
                |
                |android {
                |    namespace = "${appPackages.joinToString(".")}"
                |}
                |
            """.trimMargin()
            )
        }
}
