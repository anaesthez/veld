plugins {
    alias(libs.plugins.multiplatform)
    id("com.android.library")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "FeatureRacePresentation"
            isStatic = true
            export(libs.decompose)
            export(libs.lifecycle)
        }
    }

    sourceSets {
        commonMain.dependencies {
            with(libs) {
                implementation(decompose.extensions)
                implementation(badoo.reaktive)
                api(decompose)
                api(lifecycle)
            }
            with(libs.mvi) {
                implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
                implementation(core)
                implementation(main)
                implementation(reaktive)
            }
            implementation(project(":core:common"))
        }
    }
}

android {
    namespace = "com.nesterov.veld.feature.race.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}