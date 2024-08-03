package com.vep1940.domain.usecase

import com.vep1940.domain.repository.ObjectRepository

class GetObjects(private val repository: ObjectRepository) {
    operator fun invoke() = repository.getObjects()
}