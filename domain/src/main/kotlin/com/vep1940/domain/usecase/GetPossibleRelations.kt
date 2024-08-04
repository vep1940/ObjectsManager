package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class GetPossibleRelations(private val repository: ObjectRepository) {
    suspend operator fun invoke(id: Long) = repository.getPossibleRelations(id)
}