package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class ModifyObject(private val objectRepository: ObjectRepository) {
    suspend operator fun invoke(id: Long, name: String, description: String, type: String) =
        objectRepository.modifyObject(id = id, name = name, description = description, type = type)
}