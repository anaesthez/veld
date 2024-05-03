import com.android.build.api.dsl.ManagedVirtualDevice
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "${JavaVersion.VERSION_1_8}"
                freeCompilerArgs += "-Xjdk-release=${JavaVersion.VERSION_1_8}"
            }
        }
        //https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant {
            sourceSetTree.set(KotlinSourceSetTree.test)
            dependencies {
                debugImplementation(libs.androidx.testManifest)
                implementation(libs.androidx.junit4)
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
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        commonMain.dependencies {
            with(compose) {
                implementation(runtime)
                implementation(foundation)
                implementation(material3)
                implementation(components.resources)
                implementation(components.uiToolingPreview)
            }
            with(libs.mvi) {
                implementation(core)
                implementation(main)
                implementation(reaktive)
            }
            with(libs) {
                implementation(libres)
                implementation(decompose.extensions)
                implementation(badoo.reaktive)
                implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
                api(decompose)
                api(lifecycle)
            }
            implementation(project(":core:design-system"))
            implementation(project(":core:di"))
            implementation(project(":core:common"))

            implementation(project(":feature:spell:presentation"))
            implementation(project(":feature:spell:ui"))

            implementation(project(":feature:spell-details:presentation"))
            implementation(project(":feature:spell-details:ui"))

            implementation(project(":feature:item:presentation"))
            implementation(project(":feature:item:ui"))

            implementation(project(":feature:classes:presentation"))
            implementation(project(":feature:classes:ui"))

            implementation(project(":feature:classes-details:presentation"))
            implementation(project(":feature:classes-details:ui"))

            implementation(project(":feature:race:presentation"))
            implementation(project(":feature:race:ui"))

            implementation(project(":feature:backstory:presentation"))
            implementation(project(":feature:backstory:ui"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(libs.androidx.activityCompose)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        jsMain.dependencies {
            implementation(compose.html.core)
        }

        iosMain.dependencies {
        }
    }
}

android {
    namespace = "ru.nesterov.veld"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.compileSdk.get().toInt()

        applicationId = "ru.nesterov.veld.androidApp"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/res")
        resources.srcDirs("src/commonMain/resources")
    }
    //https://developer.android.com/studio/test/gradle-managed-devices
    @Suppress("UnstableApiUsage")
    testOptions {
        managedDevices.devices {
            maybeCreate<ManagedVirtualDevice>("pixel5").apply {
                device = "Pixel 5"
                apiLevel = 34
                systemImageSource = "aosp"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ru.nesterov.veld.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}
