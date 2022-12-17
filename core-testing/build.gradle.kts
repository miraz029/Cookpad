plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfiguration.compileSdk

    defaultConfig {
        minSdk = AppConfiguration.minSdk
        targetSdk = AppConfiguration.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }

    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }

    packagingOptions {
        resources.excludes.addAll(PackagingOptions.excludes)
    }
}

dependencies {

    implementation(Dependencies.coreKtx)

    api(Dependencies.runner)
    api(Dependencies.testCoreKtx)
    api(Dependencies.testExtJunit)
    api(Dependencies.archCoreTesting)

    api(Dependencies.espressoCore)
    api(Dependencies.espressoContrib)
    api(Dependencies.espressoIntents)
    api(Dependencies.espressoAccessibility)

    implementation(Dependencies.testUiAutomator)
}