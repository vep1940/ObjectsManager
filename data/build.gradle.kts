plugins {
    id("vep1940.android.library")
    id("vep1940.unit.test")
    id("vep1940.android.test")
    alias(libs.plugins.sql.delight)
}

android {
    namespace = "com.vep1940.data"
}

dependencies {

    implementation(projects.domain)

    implementation(libs.sql.delight.driver)

}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.vep1940")
        }
    }
}