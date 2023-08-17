/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
@file:Suppress("UnstableApiUsage")

import  com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.LibraryBuildType
import com.android.build.api.dsl.LibraryProductFlavor
import  java.util.regex.Pattern

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.app.cash.sqldelight)
    alias(libs.plugins.io.gitlab.arthubosch.detekt)
    alias(libs.plugins.org.jetbrains.kotlin.serialization)
    alias(libs.plugins.com.android.library)
    id("kotlin-parcelize")
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

android {
    namespace = "${libs.versions.namespace.get()}.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            setupBaseUrl(getCurrentFlavor())
            setupDatabase()
            setupSharedPrefName()
        }

        debug {
            setupBaseUrl(getCurrentFlavor())
            setupDatabase()
            setupSharedPrefName()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    coreLibraryDesugaring(libs.desugar.jdk.lib)

    api(libs.sqldelight.android.driver)
    with(libs.ktor){
        api(client.core)
        api(android.client)
        api(content.negotiation)
        api(json)
        api(gson)
        api(resource)
    }
    with(libs.hilt) {
        implementation(android)
        implementation(work)
        kapt(android.compiler)
        kapt(compiler)
    }
    with(libs.gms.play.service) {
        implementation(auth)
        implementation(base)
    }

    with(libs.kotlinx.coroutine) {
        implementation(android)
        implementation(core)
        implementation(play.services)
        testImplementation(test)
    }

    with(libs.chuker) {
        debugApi(debug)
        releaseApi(release)
    }


}


sqldelight{
    databases{
        create("Database"){
            packageName.set("com.bluehabit.budgetku.db")
        }
    }
}
kapt {
    correctErrorTypes = true
}


fun LibraryBuildType.setupBaseUrl(flavor: String) {
    val url = when (flavor) {
        "dev" -> "BASE_URL_DEV"
        "staging" -> "BASE_URL_STAGING"
        "production" -> "BASE_URL"
        else -> "BASE_URL_DEV"
    }
    buildConfigField("String", "BASE_URL", "\"${findProperty(url).toString()}\"")
}

fun LibraryBuildType.setupDatabase() {
    buildConfigField(
        "String",
        "DATABASE",
        "\"${findProperty("DATABASE").toString()}\""
    )
}

fun LibraryBuildType.setupSharedPrefName() {
    buildConfigField(
        "String",
        "SHARED_PREFERENCES",
        "\"${findProperty("SHARED_PREFERENCES").toString()}\""
    )
}

fun getCurrentFlavor(): String {
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()
    val pattern: Pattern = if (taskRequestsStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("bundle(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(taskRequestsStr)
    val flavor = if (matcher.find()) {
        matcher.group(1).lowercase()
    } else {
        print("NO FLAVOR FOUND")
        "dev"
    }
    return flavor
}