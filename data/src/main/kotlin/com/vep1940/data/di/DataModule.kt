package com.vep1940.data.di

import com.vep1940.data.repository.ObjectRepositoryImpl
import com.vep1940.data.repository.RelationRepositoryImpl
import com.vep1940.domain.repository.ObjectRepository
import com.vep1940.domain.repository.RelationRepository
import org.koin.dsl.module

val dataModule = module {
    single<ObjectRepository> { ObjectRepositoryImpl() }
    single<RelationRepository> { RelationRepositoryImpl() }
}