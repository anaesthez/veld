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
            baseName = "FeatureClassDetailsPresentation"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            with(libs) {
                implementation(kotlinx.coroutines.core)
                implementation(kotlinx.collections.immutable)
                implementation(decompose.extensions)
                api(decompose)
                api(lifecycle)
            }
            with(libs.mvi) {
                implementation(core)
                implementation(main)
                implementation(coroutines)
            }
            implementation(project(":feature:classes-details:domain"))
            implementation(project(":core:common"))
        }
    }
}

android {
    namespace = "ru.nesterov.veld.feature.classes_details.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}