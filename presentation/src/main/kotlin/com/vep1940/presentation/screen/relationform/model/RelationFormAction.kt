package com.vep1940.presentation.screen.relationform.model

sealed interface RelationFormAction {
    data class RelationChange(val value: Long) : RelationFormAction
    data object SaveRelation : RelationFormAction
}
