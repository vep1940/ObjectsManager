package com.vep1940.presentation.di

import com.vep1940.presentation.screen.detail.DetailScreenViewModel
import com.vep1940.presentation.screen.list.ListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::ListScreenViewModel)
    viewModelOf(::DetailScreenViewModel)
}