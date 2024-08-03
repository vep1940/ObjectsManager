package com.vep1940.presentation.screen.objectform.model

sealed interface ObjectFormAction {
    data class NameChange(val value: String) : ObjectFormAction
    data class DescriptionChange(val value: String) : ObjectFormAction
    data class TypeChange(val value: String) : ObjectFormAction
    data object SaveObject : ObjectFormAction
}
