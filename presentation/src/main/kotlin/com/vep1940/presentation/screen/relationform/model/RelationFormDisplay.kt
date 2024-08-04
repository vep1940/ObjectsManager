package com.vep1940.presentation.screen.relationform.model

import com.vep1940.presentation.model.ObjectDisplay

data class RelationFormDisplay(
    val possibleRelations: List<ObjectDisplay>,
    val selectedRelation: ObjectDisplay?,
)
