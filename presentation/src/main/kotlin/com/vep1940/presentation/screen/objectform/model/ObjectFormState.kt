package com.vep1940.presentation.screen.objectform.model

sealed interface ObjectFormState {
    data object Loading : ObjectFormState
    data class Success(val display: ObjectFormDisplay) : ObjectFormState
}