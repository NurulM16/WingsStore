package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler



fun DependencyHandler.navGraph(){
    val nav_version = "2.5.1"

    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-compose:$nav_version")
}