package com.vep1940.presentation.screen.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vep1940.presentation.screen.list.model.ListScreenAction
import com.vep1940.presentation.screen.list.model.ListScreenState

@Composable
internal fun ListScreen(
    display: ListScreenState,
    action: (ListScreenAction) -> Unit,
) {}

@Preview
@Composable
private fun ListScreenPreview(){}