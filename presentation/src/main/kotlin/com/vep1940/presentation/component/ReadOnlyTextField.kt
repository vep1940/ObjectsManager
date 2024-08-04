package com.vep1940.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ReadOnlyTextField(
    value: String,
    label: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { },
        label = { Text(label) },
        maxLines = 1,
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
    )
}