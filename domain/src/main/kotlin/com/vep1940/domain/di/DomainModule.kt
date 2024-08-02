package com.vep1940.domain.di

import com.vep1940.domain.usecase.AddObject
import com.vep1940.domain.usecase.AddRelation
import com.vep1940.domain.usecase.DeleteObject
import com.vep1940.domain.usecase.DeleteRelation
import com.vep1940.domain.usecase.ModifyObject
import com.vep1940.domain.usecase.ModifyRelation
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::AddObject)
    factoryOf(::AddRelation)
    factoryOf(::DeleteObject)
    factoryOf(::DeleteRelation)
    factoryOf(::ModifyObject)
    factoryOf(::ModifyRelation)
}