package com.vep1940.presentation.screen.objectform

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vep1940.presentation.screen.objectform.model.ObjectFormAction
import com.vep1940.presentation.screen.objectform.model.ObjectFormState

@Composable
internal fun ObjectForm(
    display: ObjectFormState,
    action: (ObjectFormAction) -> Unit,
) {
}

@Preview
@Composable
private fun ObjectFormPreview() {
}