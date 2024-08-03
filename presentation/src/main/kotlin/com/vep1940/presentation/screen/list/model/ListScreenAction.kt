package com.vep1940.presentation.screen.list.model

sealed interface ListScreenAction {
    data object AddItem : ListScreenAction
    data class SelectItem(val id: Long) : ListScreenAction
    data class DeleteItem(val id: Long) : ListScreenAction
}
