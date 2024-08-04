package com.vep1940.presentation.screen.relationform.model

sealed interface RelationFormState {
    data object Loading : RelationFormState
    data class Success(val display: RelationFormDisplay) : RelationFormState
}