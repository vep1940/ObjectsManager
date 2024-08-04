package com.vep1940.presentation.screen.list.model

import com.vep1940.presentation.model.ObjectDisplay

data class ListScreenDisplay(
    val items: List<ObjectDisplay>,
    val search: String,
)