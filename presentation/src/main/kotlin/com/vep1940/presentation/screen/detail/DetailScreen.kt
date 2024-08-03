package com.vep1940.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vep1940.presentation.screen.detail.model.DetailScreenAction
import com.vep1940.presentation.screen.detail.model.DetailScreenState

@Composable
internal fun DetailScreen(
    display: DetailScreenState,
    action: (DetailScreenAction) -> Unit,
) {}

@Preview
@Composable
private fun DetailScreenPreview() {}