package com.vep1940.data.model

import com.vep1940.domain.model.DetailedObject
import com.vep1940.domain.model.Object
import data.ObjectDto

fun List<ObjectDto>.toDomain(): List<Object> = map { it.toDomain() }

fun ObjectDto.toDomain(): Object = Object(
    id = objectId,
    name = name,
    description = description,
    type = type,
)

fun ObjectDto.toDetailedDomain(
    relations: List<ObjectDto>,
    possibleRelations: List<ObjectDto>,
): DetailedObject = DetailedObject(
    id = objectId,
    name = name,
    description = description,
    type = type,
    relations = relations.toDomain(),
    possibleRelations = possibleRelations.toDomain(),
)
