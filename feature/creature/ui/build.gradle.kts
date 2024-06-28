plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
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
            baseName = "FeatureCreatureUi"
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
                implementation(coil)
                implementation(coil.compose)
                implementation(coil.ktor)
            }
            implementation(project(":feature:creature:presentation"))
            implementation(project(":core:common"))
            implementation(project(":core:helpers"))
            implementation(project(":core:design-system"))
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