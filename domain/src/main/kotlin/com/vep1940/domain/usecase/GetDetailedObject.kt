package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class GetDetailedObject(private val objectRepository: ObjectRepository) {

    operator fun invoke(id: Long) = objectRepository.getDetailedObject(id = id)
}