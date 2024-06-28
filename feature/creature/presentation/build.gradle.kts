plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
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
            baseName = "FeatureCreaturePresentation"
            isStatic = true
            export(libs.decompose)
            export(libs.lifecycle)
        }
    }

    sourceSets {
        commonMain.dependencies {
            with(libs) {
                implementation(decompose.extensions)
                implementation(kotlinx.collections.immutable)
                implementation(kotlinx.coroutines.core)
                api(decompose)
                api(lifecycle)
            }
            with(libs.mvi) {
                implementation(core)
                implementation(main)
                implementation(coroutines)
            }
            implementation(project(":core:common"))
            implementation(project(":feature:creature:domain"))
        }
    }
}

android {
    namespace = "ru.nesterov.veld.feature.creature.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}