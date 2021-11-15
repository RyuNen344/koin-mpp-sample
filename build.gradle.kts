buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.0")
        classpath("com.android.tools.build:gradle:7.0.3")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        }
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
