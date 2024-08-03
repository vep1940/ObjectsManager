package com.vep1940.data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.vep1940.Database
import com.vep1940.data.datasource.ObjectDatasource
import com.vep1940.data.repository.ObjectRepositoryImpl
import com.vep1940.domain.repository.ObjectRepository
import data.ObjectQueries
import org.koin.dsl.module

val dataModule = module {
    single<ObjectRepository> { ObjectRepositoryImpl(get()) }
    single<ObjectDatasource> { ObjectDatasource(get()) }
    single<ObjectQueries> { get<Database>().objectQueries }
    single<Database> { Database(get()) }
    single<SqlDriver> { AndroidSqliteDriver(Database.Schema, get(), "object.db") }
}