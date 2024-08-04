package com.vep1940.presentation.screen.list.model

sealed interface ListScreenAction {
    data class Search(val value: String) : ListScreenAction
    data object AddObject : ListScreenAction
    data class SelectObject(val id: Long) : ListScreenAction
    data class DeleteObject(val id: Long) : ListScreenAction
    data object OkError : ListScreenAction
}
