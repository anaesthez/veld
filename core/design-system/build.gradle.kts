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
            baseName = "DesignSystem"
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
                implementation(libs.kotlinx.collections.immutable)
            }
        }
    }
}

android {
    namespace = "com.nesterov.veld.сore.design_system"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

libres {
    // https://github.com/Skeptick/libres#setup
    generatedClassName = "Design" // "Res" by default
    generateNamedArguments = true // false by default
    baseLocaleLanguageCode = "ru" // "en" by default
}