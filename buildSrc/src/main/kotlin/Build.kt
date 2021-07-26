object Build {
    private const val buildToolsVersion = "7.1.0-alpha03"

    const val BuildTools = "com.android.tools.build:gradle:${buildToolsVersion}"
    const val KotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val HiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"
}