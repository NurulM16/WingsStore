package dependencies.android

import dependencies.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.dataStore() {
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}