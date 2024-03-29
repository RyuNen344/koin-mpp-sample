plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.ryunen344.koin.mpp.sample.android"
        minSdk = 29
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("io.insert-koin:koin-android:3.1.3")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
}
