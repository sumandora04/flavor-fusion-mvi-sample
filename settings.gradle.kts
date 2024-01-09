pluginManagement {
    includeBuild("gradle-dependencies-manager")
    repositories {
        google()
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

rootProject.name = "Flavor Fusion"
include(":app")
include(":domain")
include(":data")
include(":presentation")
