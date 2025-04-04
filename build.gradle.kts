plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.dagger.hilt) apply false
    id("jacoco")
}

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

tasks.register<JacocoReport>("jacocoFullReport") {
    dependsOn(
        ":core:testDebugUnitTest",
        ":data:testDebugUnitTest",
        ":features:testDebugUnitTest",
        ":ui:testDebugUnitTest"
    )

    val modules = listOf("core", "data", "features", "ui")

    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "**/di/**"
    )

    val sourceDirs = files(modules.map {
        file("$rootDir/$it/src/main/java")
    })

    val classDirs = files(modules.map {
        fileTree("$rootDir/$it/build/tmp/kotlin-classes/debug") {
            exclude(fileFilter)
        }
    })

    val execDataFiles = files(modules.map {
        file("$rootDir/$it/build/outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec")
    }.filter { it.exists() })

    sourceDirectories.setFrom(sourceDirs)
    classDirectories.setFrom(classDirs)
    executionData.setFrom(execDataFiles)

    reports {
        html.required.set(true)
        xml.required.set(true)
        csv.required.set(false)
    }

    doFirst {
        if (classDirectories.files.isEmpty() || executionData.files.isEmpty()) {
            logger.warn("Jacoco: Nenhum dado de cobertura encontrado. Relatório será vazio.")
        }
    }
}
