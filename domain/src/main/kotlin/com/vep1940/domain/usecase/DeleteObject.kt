package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class DeleteObject(private val objectRepository: ObjectRepository) {
    suspend operator fun invoke(id: Long) = objectRepository.deleteObject(id = id)
}