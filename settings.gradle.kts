pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Cookpad-hiring-android-exercise"
include(":app")
include(":core-model")
include(":core-database")
include(":core-network")
include(":core-data")
include(":feature-favourite")
include(":feature-recipe")
include(":core-testing")
