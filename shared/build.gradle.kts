import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id(Plugins.AndroidLibrary)

    kotlin(Kotlin.Plugins.Multiplatform)
    kotlin(Kotlin.Plugins.Cocoapods)
    kotlin(Kotlin.Plugins.Serialization) version Kotlin.version
}

version = Application.VersionName
group = Application.ApplicationId

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Ktor.Core)
                implementation(Ktor.Logging)
                implementation(Ktor.Serialization)
                implementation(Koin.Core)
                implementation(Kotlinx.CoroutinesCore)
                implementation(Kotlinx.DateTime)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.Android)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Ktor.iOS)
            }
        }
    }
}

android {
    compileSdk = Application.CompileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Application.MinSdk
        targetSdk = Application.TargetSdk
    }
}