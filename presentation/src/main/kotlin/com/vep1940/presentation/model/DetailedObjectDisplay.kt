package com.vep1940.presentation.model

data class DetailedObjectDisplay(
    val id: Long,
    val name: String,
    val description: String,
    val type: String,
    val relations: List<ObjectDisplay>,
)
