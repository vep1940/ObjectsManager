package com.vep1940.presentation.screen.detail.model

sealed interface DetailScreenState {
    data object Loading : DetailScreenState
    data class Success(val display: DetailScreenDisplay) : DetailScreenState
    data object Error : DetailScreenState
}