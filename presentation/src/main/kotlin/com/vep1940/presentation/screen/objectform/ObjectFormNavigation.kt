package com.vep1940.presentation.screen.objectform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.vep1940.presentation.screen.objectform.model.ObjectFormAction
import org.koin.androidx.compose.koinViewModel

const val OBJECT_FORM_ROUTE = "objectForm"
const val OBJECT_ID_PARAM = "OBJECT_ID_PARAM"

fun NavGraphBuilder.objectForm(
    saveObject: () -> Unit,
) {
    dialog(
        route = "$OBJECT_FORM_ROUTE?$OBJECT_ID_PARAM={$OBJECT_ID_PARAM}",
        arguments = listOf(
            navArgument(OBJECT_ID_PARAM) {
                nullable = true
                defaultValue = null
                type = NavType.StringType
            }
        ),
    ) {
        val viewModel: ObjectFormViewModel = koinViewModel()
        val display = viewModel.display.collectAsStateWithLifecycle()

        ObjectForm(
            screenState = display.value,
            action = { action ->
                viewModel.onAction(action)
                when (action) {
                    ObjectFormAction.SaveObject -> saveObject()
                    else -> { /*NOTHING TO DO HERE*/
                    }
                }
            },
        )
    }
}

fun NavController.navigateToObjectForm(id: Long?) {
    navigate("$OBJECT_FORM_ROUTE?$OBJECT_ID_PARAM=$id")
}

internal class ObjectFormArgs private constructor(val objectId: Long?) {
    constructor(savedStateHandle: SavedStateHandle) :
            this((savedStateHandle.get<String?>(OBJECT_ID_PARAM))?.toLongOrNull())
}