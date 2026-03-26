import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)

    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlinCocoapods)

    id("maven-publish")
}

val sdkVersion = project.property("sdk.version").toString()

group = "com.noctuagames.labs.sdk"
version = sdkVersion

// Generate BuildConfig.kt so runtime code reads version from gradle.properties (single source of truth)
val generateBuildConfig by tasks.registering {
    val outputDir = layout.buildDirectory.dir("generated/buildconfig")
    val versionValue = sdkVersion

    inputs.property("sdkVersion", versionValue)
    outputs.dir(outputDir)

    doLast {
        val dir = outputDir.get().asFile.resolve("com/noctuagames/labs/sdk")
        dir.mkdirs()
        dir.resolve("BuildConfig.kt").writeText(
            """
            |package com.noctuagames.labs.sdk
            |
            |/** Auto-generated from gradle.properties — do not edit manually. */
            |object BuildConfig {
            |    const val SDK_VERSION = "$versionValue"
            |}
            """.trimMargin()
        )
    }
}

kotlin {
    val sdkBaseName = "NoctuaLabs"
    val xcf = XCFramework(sdkBaseName)

    targets.configureEach {
        compilations.configureEach {
            compileTaskProvider.get().compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }

    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    cocoapods {
        name = "NoctuaInternalSDK"
        version = sdkVersion
        summary = "Noctua internal iOS SDK"
        description = "A lightweight Kotlin Multiplatform Noctua Internal SDK with offline caching and event batching support."
        homepage = "https://github.com/NoctuaLabs/noctua-internal-native-sdk"

        authors = "Noctua Labs"
        license = "{ :type => 'MIT', :file => 'LICENSE' }"
        source = "{ :git => 'https://github.com/NoctuaLabs/noctua-internal-native-sdk.git', :tag => 'ios-sdk-v$version' }"

        ios.deploymentTarget = "14.0"
        publishDir = file("ios-sdk")

        framework {
            baseName = "NoctuaInternalSDK"
            isStatic = true
            // Dependency export
            // Uncomment and specify another project module if you have one:
            // export(project(":<your other KMP module>"))
            transitiveExport = false // This is default.
        }

        // Maps custom Xcode configuration to NativeBuildType
//        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = sdkBaseName
            freeCompilerArgs = listOf("-Xbinary=bundleId=com.noctuagames.labs.sdk")
            isStatic = true
            xcf.add(this)
        }
    }

    jvm("desktop")

    room {
        schemaDirectory("$projectDir/schemas")
    }

    sourceSets {
        val desktopMain by getting

        commonMain {
            kotlin.srcDir(generateBuildConfig.map { layout.buildDirectory.dir("generated/buildconfig") })
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)

        }

        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.bundles.ktor)
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.kotlinx.datetime)
            api(libs.koin.core)
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutines.test)
            }
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.core)
        }

        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.ktor.client.okhttp)
        }

        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        dependencies {
            add("kspAndroid", libs.androidx.room.compiler)
            add("kspIosSimulatorArm64", libs.androidx.room.compiler)
            add("kspIosX64", libs.androidx.room.compiler)
            add("kspIosArm64", libs.androidx.room.compiler)
            add("kspDesktop", libs.androidx.room.compiler)
        }
    }
}

android {
    namespace = "com.noctuagames.labs.sdk"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        aarMetadata {
            minCompileSdk = 32
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

publishing {
    repositories {
        maven {
            name = "GitLab"
            url = uri("https://gitlab.com/api/v4/projects/72442359/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                name = "Job-Token"
                value = System.getenv("CI_JOB_TOKEN")
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }
}

mavenPublishing {

    coordinates(group.toString(), "native", sdkVersion)

    configure(
        AndroidSingleVariantLibrary(
            variant = "release",
            sourcesJar = true,
            publishJavadocJar = true
        )
    )

    signAllPublications()

    pom {
        name = "Noctua Labs"
        description = "Noctua Labs SDK"
        inceptionYear = "2025"
        url = "https://github.com/NoctuaLabs/noctua-internal-native-sdk"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "noctualabs"
                name = "NoctuaLabs"
                url = "https://github.com/NoctuaLabs"
            }
        }
        scm {
            url = "https://github.com/NoctuaLabs/noctua-internal-native-sdk"
            connection = "scm:git:git://github.com/noctualabs/noctua-internal-native-sdk"
            developerConnection = "scm:git:ssh://git@github.com:NoctuaLabs/noctua-internal-native-sdk.git"
        }
    }
}
