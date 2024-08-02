plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.plugin)
    compileOnly(libs.kotlin.plugin)
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = "vep1940.android.app"
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("androidLibrary") {
            id = "vep1940.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "vep1940.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "vep1940.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("unitTest") {
            id = "vep1940.unit.test"
            implementationClass = "UnitTestConventionPlugin"
        }
        register("androidTest") {
            id = "vep1940.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
    }
}