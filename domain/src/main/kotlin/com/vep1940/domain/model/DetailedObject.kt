package com.vep1940.domain.model

data class DetailedObject(
    val id: Long,
    val name: String,
    val description: String,
    val type: String,
    val relations: List<Object>,
)
