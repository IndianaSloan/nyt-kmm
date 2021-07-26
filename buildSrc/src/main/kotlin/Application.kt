object Application {

    object Variants {
        const val Debug = "debug"
        const val Release = "release"
    }

    const val AppName = "NYT"
    const val ApplicationId = "com.ciaransloan.nytkmm"
    const val AndroidPackageName = "$ApplicationId.android"
    const val iOSBundleId = "$ApplicationId.ios"
    const val VersionMajor = 1
    const val VersionMinor = 0
    const val VersionCode = 1
    const val VersionName = "$VersionMajor.$VersionMinor.$VersionCode"
    const val MinSdk = 21
    const val CompileSdk = 30
    const val TargetSdk = 30
}