package com.vep1940.objectsmanager

import androidx.lifecycle.SavedStateHandle
import com.vep1940.objectsmanager.di.appModule
import org.junit.jupiter.api.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

@KoinExperimentalAPI
class InjectionTest : KoinTest {

    @Test
    fun checkModules() {
        appModule.verify(
            extraTypes = listOf(SavedStateHandle::class)
        )
    }
}