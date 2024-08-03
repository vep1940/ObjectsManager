package com.vep1940.presentation.model

import com.vep1940.domain.model.DetailedObject
import com.vep1940.domain.model.Object

fun List<Object>.toPresentation() = map { it.toPresentation() }

fun Object.toPresentation() =
    ObjectDisplay(id = id, name = name, description = description, type = type)

fun DetailedObject.toPresentation() =
    DetailedObjectDisplay(
        id = id,
        name = name,
        description = description,
        type = type,
        relations = relations.toPresentation(),
    )