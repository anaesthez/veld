plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.libres)
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
            baseName = "FeatureClassDetailsUi"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            with(compose) {
                implementation(runtime)
                implementation(foundation)
                implementation(material3)
                implementation(ui)
            }
            with(libs) {
                implementation(kotlinx.collections.immutable)
                implementation(decompose.extensions)
                implementation(libres)
            }
            implementation(project(":feature:classes-details:presentation"))
            implementation(project(":core:design-system"))
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