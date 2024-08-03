package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class ModifyRelation(private val objectRepository: ObjectRepository) {
    suspend operator fun invoke(
        oldObjectId1: Long,
        oldObjectId2: Long,
        newObjectId1: Long,
        newObjectId2: Long,
    ) {
        objectRepository.modifyRelation(oldObjectId1, oldObjectId2, newObjectId1, newObjectId2)
    }
}