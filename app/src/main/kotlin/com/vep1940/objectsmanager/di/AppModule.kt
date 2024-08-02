package com.vep1940.objectsmanager.di

import com.vep1940.data.di.dataModule
import com.vep1940.domain.di.domainModule
import com.vep1940.presentation.di.presentationModule
import org.koin.dsl.module

val appModule = module {
    includes(presentationModule, domainModule, dataModule)
}