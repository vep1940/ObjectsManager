plugins {
    id("vep1940.android.library")
    id("vep1940.android.compose")
    id("vep1940.unit.test")
    id("vep1940.android.test")
}

android {
    namespace = "com.vep1940.presentation"
}

dependencies {

    implementation(projects.domain)

}