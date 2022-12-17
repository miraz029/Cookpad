object AppConfiguration {

    const val minSdk = 21
    const val compileSdk = 31
    const val buildToolVersion = "31.0.0"
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val releaseDebugging = false
}

object PackagingOptions {
    val excludes = setOf (
        "META-INF/licenses/**",
        "META-INF/AL2.0",
        "META-INF/LGPL2.1",
        "LICENSE.txt",
        "META-INF/LICENSE.md",
        "META-INF/LICENSE-notice.md"
    )
}