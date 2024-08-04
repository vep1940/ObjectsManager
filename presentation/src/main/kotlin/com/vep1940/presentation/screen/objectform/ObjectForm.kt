package com.vep1940.presentation.screen.objectform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vep1940.presentation.R
import com.vep1940.presentation.component.LoadingScreen
import com.vep1940.presentation.component.TextInput
import com.vep1940.presentation.screen.objectform.model.ObjectFormAction
import com.vep1940.presentation.screen.objectform.model.ObjectFormDisplay
import com.vep1940.presentation.screen.objectform.model.ObjectFormState

@Composable
internal fun ObjectForm(
    screenState: ObjectFormState,
    action: (ObjectFormAction) -> Unit,
) {
    when (screenState) {
        ObjectFormState.Loading -> LoadingScreen()
        is ObjectFormState.Success -> Success(display = screenState.display, action = action)
    }
}

@Composable
private fun Success(display: ObjectFormDisplay, action: (ObjectFormAction) -> Unit) {
    Card {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(horizontal = 16.dp),
        ) {
            TextInput(
                value = display.type,
                label = stringResource(id = R.string.type_text_input_label),
                placeholder = stringResource(id = R.string.type_text_input_placeholder),
                onValueChange = { newValue -> action(ObjectFormAction.TypeChange(newValue)) },
                hasError = display.hasTypeError,
                isRequired = true,
            )
            TextInput(
                value = display.name,
                label = stringResource(id = R.string.name_text_input_label),
                placeholder = stringResource(id = R.string.name_text_input_placeholder),
                onValueChange = { newValue -> action(ObjectFormAction.NameChange(newValue)) },
                hasError = display.hasNameError,
                isRequired = true,
            )
            TextInput(
                value = display.description,
                label = stringResource(id = R.string.description_text_input_label),
                placeholder = stringResource(id = R.string.description_text_input_placeholder),
                onValueChange = { newValue -> action(ObjectFormAction.DescriptionChange(newValue)) },
            )
            Button(
                onClick = { action(ObjectFormAction.SaveObject) },
                modifier = Modifier.fillMaxWidth(),
                enabled = display.hasNameError.not() && display.hasTypeError.not(),
            ) {
                Text(text = stringResource(id = R.string.save_config))
            }
        }
    }
}

@Preview
@Composable
private fun ObjectFormPreview() {

    var display by remember {
        mutableStateOf(
            ObjectFormDisplay(
                name = "",
                hasNameError = true,
                description = "",
                type = "",
                hasTypeError = true,
            )
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ObjectForm(
            screenState = ObjectFormState.Success(display),
            action = { action ->
                when (action) {
                    is ObjectFormAction.DescriptionChange -> display =
                        display.copy(description = action.value)

                    is ObjectFormAction.NameChange -> display = display.copy(name = action.value)
                    is ObjectFormAction.TypeChange -> display = display.copy(type = action.value)
                    else -> {}
                }
            }
        )
    }
}