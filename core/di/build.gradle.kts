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
            baseName = "CoreDI"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            with(libs.ktor) {
                implementation(client.logging)
                implementation(client.content.negotiation)
                implementation(client.serialization.json)
                implementation(core)
            }
            implementation(project(":core:common"))

            implementation(project(":datasource:api:network"))
            implementation(project(":datasource:impl:network"))

            implementation(project(":feature:spell:data"))
            implementation(project(":feature:spell:domain"))
            implementation(project(":feature:spell:presentation"))

            implementation(project(":feature:spell-details:data"))
            implementation(project(":feature:spell-details:domain"))
            implementation(project(":feature:spell-details:presentation"))
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.java)
        }
        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}

android {
    namespace = "com.nesterov.veld.core.di"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}