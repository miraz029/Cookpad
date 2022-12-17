plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    compileSdk = AppConfiguration.compileSdk

    defaultConfig {
        minSdk = AppConfiguration.minSdk
        targetSdk = AppConfiguration.targetSdk

        testInstrumentationRunner = "com.cookpad.hiring.android.feature.favorite.CustomTestRunner"
    }

    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        //execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
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
    implementation(project(":core-data"))
    implementation(project(":core-testing"))

    implementation(Dependencies.coroutine)

    implementation(Dependencies.material)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appcompat)

    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.lifecycleLiveDataKtx)
    implementation(Dependencies.lifecycleViewModelKtx)

    implementation(Dependencies.swipeRefreshLayout)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.recyclerview)

    // Image loading
    implementation(Dependencies.glide)

    // Dependency injection
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
    androidTestImplementation(Dependencies.hiltAndroidTesting)
    kaptAndroidTest(Dependencies.hiltAndroidCompiler)

    // Testing
    testImplementation(Dependencies.runner)
    debugImplementation(Dependencies.fragmentTesting)

    testImplementation(Dependencies.testCoreKtx)
    testImplementation(Dependencies.testExtJunit)
    testImplementation(Dependencies.archCoreTesting)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.kotlinxCoroutinesTest)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.mockitoInline)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.robolectric)
    testImplementation(Dependencies.testUiAutomator)
    testImplementation(Dependencies.espressoCore)

    androidTestImplementation(Dependencies.runner)
    androidTestImplementation(Dependencies.testCoreKtx)
    androidTestImplementation(Dependencies.archCoreTesting)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.kotlinxCoroutinesTest)
    androidTestImplementation(Dependencies.testUiAutomator)
    androidTestImplementation(Dependencies.espressoCore)
//    androidTestImplementation(Dependencies.mockitoCore)
    androidTestImplementation(Dependencies.testExtJunit)
//    androidTestImplementation(Dependencies.mockitoKotlin)
//    androidTestImplementation(Dependencies.mockitoAndroid)
}