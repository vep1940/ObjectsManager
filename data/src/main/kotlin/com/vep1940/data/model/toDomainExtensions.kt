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

fun List<ObjectDto>.toDetailedDomain(): DetailedObject? = getOrNull(0)?.let {
    DetailedObject(
        id = it.objectId,
        name = it.name,
        description = it.description,
        type = it.type,
        relations = takeLast(size - 1).toDomain()
    )
}
