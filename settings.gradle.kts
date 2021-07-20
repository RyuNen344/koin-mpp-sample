pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter()
    }
}

plugins {
    id("com.louiscad.complete-kotlin") version "1.0.0"
}

rootProject.name = "KoinMppSample"
include(":androidApp")
include(":shared")
