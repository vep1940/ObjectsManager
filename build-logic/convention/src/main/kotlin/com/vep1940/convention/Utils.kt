package com.vep1940.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.getLibrary(libraryAlias: String) =
    findLibrary(libraryAlias).get()

internal fun VersionCatalog.getVersion(versionAlias: String) =
    findVersion(versionAlias).get().requiredVersion

internal fun VersionCatalog.getPluginId(pluginAlias: String) =
    findPlugin(pluginAlias).get().get().pluginId

internal fun getJavaVersion() = JavaVersion.VERSION_17

internal fun CommonExtension<*, *, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}