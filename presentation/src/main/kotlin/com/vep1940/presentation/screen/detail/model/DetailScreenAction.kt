package com.vep1940.presentation.screen.detail.model

sealed interface DetailScreenAction {
    data class ModifyObject(val id: Long) : DetailScreenAction
    data class AddRelation(val id: Long) : DetailScreenAction
    data class ModifyRelation(val id: Long, val relatedId: Long) : DetailScreenAction
    data class DeleteRelation(val objectId1: Long, val objectId2: Long) : DetailScreenAction
    data object OkError : DetailScreenAction
}
