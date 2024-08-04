package com.vep1940.presentation.di

import com.vep1940.presentation.screen.detail.DetailScreenViewModel
import com.vep1940.presentation.screen.list.ListScreenViewModel
import com.vep1940.presentation.screen.objectform.ObjectFormViewModel
import com.vep1940.presentation.screen.relationform.RelationFormViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::ListScreenViewModel)
    viewModelOf(::DetailScreenViewModel)
    viewModelOf(::ObjectFormViewModel)
    viewModelOf(::RelationFormViewModel)
}