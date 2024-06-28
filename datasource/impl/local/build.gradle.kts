plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.room)
    id("com.google.devtools.ksp")
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
            baseName = "DatasourceLocalSpellImpl"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.sqlite)
            implementation(project(":core:common"))
            implementation(project(":datasource:api:local"))
        }
    }
}

android {
    namespace = "com.nesterov.veld.datasource.spell.impl.local"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
// Room set up
dependencies {
    ksp(libs.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}