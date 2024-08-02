package com.vep1940.presentation.screen.list.model

data class ListScreenAction(
    val onClickAdd: () -> Unit,
    val onClickNavigate: () -> Unit,
)
