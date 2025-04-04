plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    id("jacoco")
}

android {
    namespace = "br.com.las.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            enableUnitTestCoverage = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    sourceSets {
        getByName("main") {
            assets.srcDirs("src/main/assets") // ✅ importante para LocalJsonProductDataSource
        }
    }
}

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "**/di/**"
    )

    val kotlinClasses = layout.buildDirectory.dir("tmp/kotlin-classes/debug").get().asFile
    val javaClasses = layout.buildDirectory.dir("intermediates/classes/debug").get().asFile
    val mainSrc = "$projectDir/src/main/java"
    val execData = layout.buildDirectory.file("outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec").get().asFile

    sourceDirectories.setFrom(files(mainSrc))

    classDirectories.setFrom(
        files(
            fileTree(kotlinClasses) { exclude(fileFilter) },
            fileTree(javaClasses) { exclude(fileFilter) }
        )
    )

    executionData.setFrom(files(execData))

    doFirst {
        if (classDirectories.files.isEmpty() || !execData.exists()) {
            logger.warn("⚠️ Nenhuma cobertura gerada no módulo 'data'.")
        }
    }
}

dependencies {
    implementation(project(":core"))

    implementation(libs.dagger.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.ioMockk)
    testImplementation(libs.kotlin.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
