package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class AddRelation(private val objectRepository: ObjectRepository) {
    suspend operator fun invoke(objectId1: Long, objectId2: Long) =
        objectRepository.addRelation(objectId1 = objectId1, objectId2 = objectId2)
}