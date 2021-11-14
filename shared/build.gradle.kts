import org.jetbrains.kotlin.gradle.plugin.mpp.Framework.BitcodeEmbeddingMode.BITCODE
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget : (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "kotlin multiplatform koin"
        homepage = "https://github.com/RyuNen344"
        ios.deploymentTarget = "14.2"
        framework {
            baseName = "shared"

            // Configure the framework which is generated internally by cocoapods plugin
            transitiveExport = true
            export("io.insert-koin:koin-core:3.1.3")
            export("io.github.aakira:napier:2.1.0")
            export("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

            embedBitcode(BITCODE)
        }
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("io.insert-koin:koin-core:3.1.3")
                api("io.github.aakira:napier:2.1.0")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1-new-mm-dev2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
                implementation("io.ktor:ktor-client-core:1.6.2-native-mm-eap-196")
                implementation("io.ktor:ktor-client-logging:1.6.2-native-mm-eap-196")
                implementation("io.ktor:ktor-client-json:1.6.2-native-mm-eap-196")
                implementation("io.ktor:ktor-client-serialization:1.6.2-native-mm-eap-196")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.insert-koin:koin-test:3.1.3")
            }
        }
        val androidMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1-new-mm-dev2")
                implementation("io.ktor:ktor-client-okhttp:1.6.2-native-mm-eap-196")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation("io.insert-koin:koin-test-junit4:3.1.3")
                implementation("io.mockk:mockk:1.12.0")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.2-native-mm-eap-196")
            }
        }
        val iosTest by getting
    }

    targets.withType<KotlinNativeTarget> {
        // export KDoc
        compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 29
        targetSdk = 31
    }
}
