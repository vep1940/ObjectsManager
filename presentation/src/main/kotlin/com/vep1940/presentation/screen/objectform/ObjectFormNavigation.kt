package com.vep1940.presentation.screen.objectform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.vep1940.presentation.screen.objectform.model.ObjectFormAction
import org.koin.androidx.compose.koinViewModel

const val OBJECT_FORM_ROUTE = "objectForm"
const val OBJECT_ID_PARAM = "OBJECT_ID_PARAM"

fun NavGraphBuilder.objectForm(
    navController: NavController,
) {
    dialog("$OBJECT_FORM_ROUTE?$OBJECT_ID_PARAM={$OBJECT_ID_PARAM}") {
        val viewModel: ObjectFormViewModel = koinViewModel()
        val display = viewModel.display.collectAsStateWithLifecycle()

        ObjectForm(
            display = display.value,
            action = { action ->
                viewModel.onAction(action)
                when (action) {
                    ObjectFormAction.SaveObject -> navController.popBackStack()
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
            this((savedStateHandle.get<String?>(OBJECT_ID_PARAM))?.toLong())
}