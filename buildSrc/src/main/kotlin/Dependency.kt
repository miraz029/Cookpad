object BuildPlugins {
    val androidGradlePlugin by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlinGradlePlugin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val navArgsGradlePlugin by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}" }
    val hiltGradlePlugin by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}" }
}

object Dependencies {
    val material by lazy { "com.google.android.material:material:${Versions.materialVersion}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtxVersion}" }
    val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompatVersion}" }

    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtxVersion}" }
    val lifecycleLiveDataKtx by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtxVersion}" }
    val lifecycleViewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtxVersion}" }

    val swipeRefreshLayout by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}" }
    val recyclerview by lazy { "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}" }

    //
    val coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}" }

    // Navigation
    val navigationFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}" }
    val navigationUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}" }

    // Networking
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
    val converterMoshi by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}" }
    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}" }

    // Image loading
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glideVersion}" }

    // Room Database
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.roomVersion}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.roomVersion}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.roomVersion}" }

    // Dependency injection
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltVersion}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hiltVersion}" }

    // Testing
    val runner by lazy { "androidx.test:runner:${Versions.testRunnerVersion}" }
    val fragmentTesting by lazy { "androidx.fragment:fragment-testing:${Versions.testFragmentVersion}" }
    val hiltAndroidTesting by lazy { "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}" }

    val testExtJunit by lazy { "androidx.test.ext:junit:${Versions.androidxJunitVersion}" }
    val testCoreKtx by lazy { "androidx.test:core-ktx:${Versions.testCoreKtxVersion}" }
    val archCoreTesting by lazy { "androidx.arch.core:core-testing:${Versions.archCoreTesting}" }
    val junit by lazy { "junit:junit:${Versions.junitVersion}" }
    val kotlinxCoroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}" }
    val kotlinCoroutineCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}" }

    val mockitoCore by lazy { "org.mockito:mockito-core:${Versions.mockitoVersion}" }
    val mockitoInline by lazy { "org.mockito:mockito-inline:${Versions.mockitoVersion}" }
    val mockitoAndroid by lazy { "org.mockito:mockito-android:${Versions.mockitoVersion}" }
    val mockitoKotlin by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlinVersion}" }


    val robolectric by lazy { "org.robolectric:robolectric:${Versions.robolectricVersion}" }

    val testUiAutomator by lazy { "androidx.test.uiautomator:uiautomator:${Versions.testUiAutomatorVersion}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}" }
    val espressoContrib by lazy { "androidx.test.espresso:espresso-contrib:${Versions.espressoCoreVersion}" }
    val espressoIntents by lazy { "androidx.test.espresso:espresso-intents:${Versions.espressoCoreVersion}" }
    val espressoAccessibility by lazy { "androidx.test.espresso:espresso-accessibility:${Versions.espressoCoreVersion}" }
}