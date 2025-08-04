import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)

    //Custom Library
    alias(libs.plugins.jetbrains.kotlin.serialization)
//    alias(libs.plugins.ksp)
//    alias(libs.plugins.room)

    id("maven-publish")

}

val sdkVersion = File("version.txt").readText().trim()

group = "gg.noctua.internal"
version = sdkVersion

kotlin {
    val sdkBaseName = "NoctuaInternal"
    val xcf = XCFramework(sdkBaseName)

    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = sdkBaseName
            freeCompilerArgs = listOf("-Xbinary=bundleId=gg.noctua.internal")
            isStatic = true
            xcf.add(this)
        }
    }

    jvm("desktop")

//    room {
//        schemaDirectory("$projectDir/schemas")
//    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
//            implementation(libs.androidx.room.runtime)
//            implementation(libs.sqlite.bundled)
            implementation(libs.bundles.ktor)
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.kotlinx.datetime)
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
//            ksp(libs.androidx.room.compiler)
        }
    }
}

android {
    namespace = "gg.noctua.internal"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

//    signAllPublications()

    coordinates(group.toString(), "sdk", sdkVersion)

    pom {
        name = "Noctua Internal"
        description = "Noctua Internal SDK"
        inceptionYear = "2025"
        url = "https://github.com/kotlin/multiplatform-library-template/"
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
