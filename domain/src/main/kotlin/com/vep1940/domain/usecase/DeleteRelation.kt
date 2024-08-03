package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class DeleteRelation(private val repository: ObjectRepository) {
    suspend operator fun invoke(objectId1: Long, objectId2: Long) =
        repository.removeRelation(objectId1 = objectId1, objectId2 = objectId2)
}