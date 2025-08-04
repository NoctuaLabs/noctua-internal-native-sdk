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

group = "gg.noctua.analytics"
version = "1.0.0"

kotlin {
    val sdkBaseName = "NoctuaAnalytics"
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
            freeCompilerArgs = listOf("-Xbinary=bundleId=gg.noctua.analytics")
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
    namespace = "gg.noctua.analytics"
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

    coordinates(group.toString(), "sdk", version.toString())

    pom {
        name = "Noctua Analytics"
        description = "Noctua Analytics SDK"
        inceptionYear = "2025"
        url = "https://github.com/kotlin/multiplatform-library-template/"
        licenses {
            license {
                name = "XXX"
                url = "YYY"
                distribution = "ZZZ"
            }
        }
        developers {
            developer {
                id = "XXX"
                name = "NoctuaLabs"
                url = "ZZZ"
            }
        }
        scm {
            url = "XXX"
            connection = "YYY"
            developerConnection = "ZZZ"
        }
    }
}
