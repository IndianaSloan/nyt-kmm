object Compose {
    const val version = "1.0.0-rc02"

    const val Runtime = "androidx.compose.runtime:runtime:${version}"
    const val RuntimeLiveData = "androidx.compose.runtime:runtime-livedata:${version}"
    const val UI = "androidx.compose.ui:ui:${version}"
    const val Material = "androidx.compose.material:material:${version}"
    const val UITooling = "androidx.compose.ui:ui-tooling:${version}"
    const val Foundation = "androidx.compose.foundation:foundation:${version}"
    const val Compiler = "androidx.compose.compiler:compiler:${version}"
    const val MaterialIconsCore = "androidx.compose.material:material-icons-core:${version}"
    const val MaterialIconsExtended = "androidx.compose.material:material-icons-extended:${version}"

    private const val constraintLayoutComposeVersion = "1.0.0-beta01"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${constraintLayoutComposeVersion}"

    private const val composeActivitiesVersion = "1.3.0-rc02"
    const val Activity = "androidx.activity:activity-compose:${composeActivitiesVersion}"

    private const val composeNavigationVersion = "2.4.0-alpha05"
    const val Navigation = "androidx.navigation:navigation-compose:${composeNavigationVersion}"
}