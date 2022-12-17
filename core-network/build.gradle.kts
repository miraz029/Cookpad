plugins {
    id("com.android.library")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
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
    implementation(project(":core-model"))

    // Networking
    implementation(Dependencies.retrofit)
    implementation(Dependencies.converterMoshi)
    implementation(Dependencies.moshiKotlin)

    // Dependency injection
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}