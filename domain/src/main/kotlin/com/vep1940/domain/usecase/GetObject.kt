package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class GetObject(private val objectRepository: ObjectRepository) {
    suspend operator fun invoke(objectId: Long) = objectRepository.getObject(id = objectId)
}