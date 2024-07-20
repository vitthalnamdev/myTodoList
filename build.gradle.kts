// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.kotlin.android) apply false
//    id("com.google.dagger.hilt.android") version "2.44" apply false
//    id("androidx.room") version "2.6.1" apply false
//}

buildscript {
    val composeUiVersion by extra("1.4.2")
    val hiltVersion by extra("2.45")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.5.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.1" apply false
    id("com.android.library") version "8.5.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}

