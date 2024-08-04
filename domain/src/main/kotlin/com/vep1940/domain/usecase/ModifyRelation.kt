package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class ModifyRelation(private val objectRepository: ObjectRepository) {
    suspend operator fun invoke(
        objectId: Long,
        oldObjectId2: Long,
        newObjectId2: Long,
    ) {
        objectRepository.modifyRelation(objectId, oldObjectId2, newObjectId2)
    }
}