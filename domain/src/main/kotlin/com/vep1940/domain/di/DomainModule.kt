package com.vep1940.domain.di

import com.vep1940.domain.usecase.AddObject
import com.vep1940.domain.usecase.AddRelation
import com.vep1940.domain.usecase.DeleteObject
import com.vep1940.domain.usecase.DeleteRelation
import com.vep1940.domain.usecase.GetDetailedObject
import com.vep1940.domain.usecase.GetObject
import com.vep1940.domain.usecase.GetObjects
import com.vep1940.domain.usecase.GetPossibleRelations
import com.vep1940.domain.usecase.ModifyObject
import com.vep1940.domain.usecase.ModifyRelation
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetObject)
    factoryOf(::GetObjects)
    factoryOf(::GetDetailedObject)
    factoryOf(::GetPossibleRelations)
    factoryOf(::AddObject)
    factoryOf(::AddRelation)
    factoryOf(::DeleteObject)
    factoryOf(::DeleteRelation)
    factoryOf(::ModifyObject)
    factoryOf(::ModifyRelation)
}