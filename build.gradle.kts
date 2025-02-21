// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    //safe args
    id("androidx.navigation.safeargs.kotlin") version "2.8.4" apply false
    //Dagger-hilt
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    //ksp
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    //Room
    id("androidx.room") version "2.6.1" apply false

}