plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    kotlin("android")
}

android {
    compileSdk = AppConfiguration.compileSdk

    defaultConfig {
        applicationId = "com.cookpad.hiring.android"
        minSdk = AppConfiguration.minSdk
        targetSdk = AppConfiguration.targetSdk
        versionCode = AppConfiguration.versionCode
        versionName = AppConfiguration.versionName

        testInstrumentationRunner = "com.cookpad.hiring.android.CustomTestRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
        testFunctionalTest = true
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }

    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }

    buildFeatures {
        viewBinding = true
    }

    lint {
        isAbortOnError = false
        htmlReport = true
        htmlOutput = file("$rootDir/reports/lint/lint-report.html")
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
//        execution = "ANDROID_TEST_ORCHESTRATOR"
    }

    packagingOptions {
        resources.excludes.addAll(PackagingOptions.excludes)
    }
}

dependencies {
    implementation(project(":feature-favourite"))
    implementation(project(":feature-recipe"))

    implementation(project(":core-model"))
    implementation(project(":core-data"))
    implementation(project(":core-testing"))

    implementation(Dependencies.material)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appcompat)

    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.lifecycleLiveDataKtx)
    implementation(Dependencies.lifecycleViewModelKtx)

    implementation(Dependencies.swipeRefreshLayout)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.recyclerview)
    implementation(Dependencies.coroutine)

    // Navigation
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUiKtx)

    // Image loading
    implementation(Dependencies.glide)

    // Dependency injection
    implementation(Dependencies.hiltAndroid)
    implementation("com.google.ar:core:1.23.0")
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
//    androidTestImplementation(Dependencies.robolectric)
}