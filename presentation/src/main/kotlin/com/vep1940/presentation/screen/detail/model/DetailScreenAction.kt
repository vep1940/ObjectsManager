package com.vep1940.presentation.screen.detail.model

sealed interface DetailScreenAction {
    data class ModifyObject(
        val id: Long,
        val name: String,
        val description: String,
        val type: String
    ) : DetailScreenAction

    data class AddRelation(val objectId1: Long, val objectId2: Long) : DetailScreenAction
    data class ModifyRelation(
        val oldObjectId1: Long,
        val oldObjectId2: Long,
        val newObjectId1: Long,
        val newObjectId2: Long,
    ) : DetailScreenAction

    data class DeleteRelation(val objectId1: Long, val objectId2: Long) : DetailScreenAction
}
