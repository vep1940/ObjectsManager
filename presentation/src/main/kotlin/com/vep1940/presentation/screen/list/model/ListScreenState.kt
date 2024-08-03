package com.vep1940.presentation.screen.list.model

sealed interface ListScreenState {
    data object Loading : ListScreenState
    data class Success(val display: ListScreenDisplay) : ListScreenState
    data object Error : ListScreenState
}