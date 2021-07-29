buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.KotlinGradlePlugin)
        classpath(Build.BuildTools)
        classpath(Build.HiltGradlePlugin)
        classpath(Build.SqlDelightPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}