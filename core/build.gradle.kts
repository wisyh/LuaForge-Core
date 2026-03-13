plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.luaforge.studio.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 23

        vectorDrawables {
            useSupportLibrary = true
        }

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isShrinkResources = false

            ndk {
                abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a"))
            }
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false

            ndk {
                abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a"))
            }
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "DebugProbesKt.bin"
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Material Design
    implementation(libs.material)

    // AndroidX Misc
    implementation(libs.activity)
    implementation(libs.appcompat)
    implementation(libs.annotation)
    implementation(libs.collection)
    implementation(libs.constraintlayout)
    implementation(libs.coordinatorlayout)
    implementation(libs.customview)
    implementation(libs.documentfile)
    implementation(libs.drawerlayout)
    implementation(libs.dynamicanimation)
    implementation(libs.fragment)
    implementation(libs.gridlayout)
    implementation(libs.legacy.support.core.ui)
    implementation(libs.legacy.support.core.utils)
    implementation(libs.localbroadcastmanager)
    implementation(libs.palette)
    implementation(libs.preference)
    implementation(libs.startup.runtime)
    implementation(libs.swiperefreshlayout)
    implementation(libs.slidingpanelayout)
    implementation(libs.recyclerview)
    implementation(libs.transition)
    implementation(libs.window)
    implementation(libs.viewpager)
    implementation(libs.viewpager2)
    implementation(libs.cardview)
    implementation(libs.browser)

    // Networking & Parsing
    implementation(libs.gson)

    // Image Loading (Glide)
    implementation(libs.glide)
    implementation(libs.okhttp3.integration)

    // HTTP Client (OkHttp)
    implementation(libs.okhttp)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                
                groupId = "io.github.wisyh"
                artifactId = "LuaForge-Core"
                version = "1.0.0"
            }
        }
    }
}