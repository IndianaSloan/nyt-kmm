plugins {
    id(Plugins.AndroidApplication)
    id(Plugins.Hilt)

    kotlin(Kotlin.Plugins.Android)
    kotlin(Kotlin.Plugins.Kapt)
    kotlin(Kotlin.Plugins.Serialization) version Kotlin.version
}

android {
    compileSdk = Application.CompileSdk
    defaultConfig {
        applicationId = Application.AndroidPackageName
        minSdk = Application.MinSdk
        targetSdk = Application.TargetSdk
        versionCode = Application.VersionCode
        versionName = Application.VersionName
    }
    buildTypes {
        getByName(Application.Variants.Debug) {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false

            resValue("string", "app_name", "${Application.AppName} (Local)")
            addManifestPlaceholders(mapOf("branchIsTest" to true))
        }
        getByName(Application.Variants.Release) {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            resValue("string", "app_name", Application.AppName)
            addManifestPlaceholders(mapOf("branchIsTest" to false))
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.version
    }
}

dependencies {
    coreLibraryDesugaring(Android.DesugaringLibs)

    implementation(project(":shared"))

    implementation(Accompanist.Coil)
    implementation(Accompanist.Pager)
    implementation(Accompanist.SystemUIController)

    implementation(Androidx.AppCompat)
    implementation(Androidx.LifecycleRuntime)
    implementation(Androidx.LifecycleViewModelCompose)

    implementation(Coil.Coil)

    implementation(Compose.Activity)
    implementation(Compose.Compiler)
    implementation(Compose.ConstraintLayout)
    implementation(Compose.Foundation)
    implementation(Compose.Material)
    implementation(Compose.MaterialIconsCore)
    implementation(Compose.MaterialIconsExtended)
    implementation(Compose.Navigation)
    implementation(Compose.Runtime)
    implementation(Compose.RuntimeLiveData)
    implementation(Compose.UI)
    implementation(Compose.UITooling)

    implementation(Google.Gson)

    implementation(Hilt.HiltAndroid)
    implementation(Hilt.HiltNavigation)
    kapt(Hilt.HiltCompiler)

    implementation(Koin.Android)
    implementation(Koin.Core)
}