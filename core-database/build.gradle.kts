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

    // Room Database
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    annotationProcessor(Dependencies.roomCompiler)
    kapt(Dependencies.roomCompiler)

    // Dependency injection
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)

    // Testing
    testImplementation(Dependencies.runner)
    testImplementation(Dependencies.testCoreKtx)
    testImplementation(Dependencies.testExtJunit)
    testImplementation(Dependencies.archCoreTesting)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.kotlinxCoroutinesTest)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.mockitoInline)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.robolectric)

    androidTestImplementation(Dependencies.testExtJunit)
    androidTestImplementation(Dependencies.espressoCore)
}