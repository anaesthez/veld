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
            baseName = "DatasourceNetworkSpellApi"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            with(libs) {
                implementation(kotlinx.serialization.json)
                implementation(kotlinx.coroutines.core)
                implementation(libs.ktor.core)
                implementation(project(":core:common"))
            }
        }
    }
}

android {
    namespace = "com.nesterov.veld.datasource.spell.api.network"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}