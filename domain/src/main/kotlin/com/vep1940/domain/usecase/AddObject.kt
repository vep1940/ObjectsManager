package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class AddObject(private val objectRepository: ObjectRepository) {
    suspend operator fun invoke(name: String, description: String, type: String) =
        objectRepository.addObject(name = name, description = description, type = type)
}