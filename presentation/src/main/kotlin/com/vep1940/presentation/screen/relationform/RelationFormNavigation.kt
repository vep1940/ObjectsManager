package com.vep1940.presentation.screen.relationform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.vep1940.presentation.screen.relationform.model.RelationFormAction
import org.koin.androidx.compose.koinViewModel

const val RELATION_FORM_ROUTE = "relationForm"
const val OBJECT_ID_PARAM = "OBJECT_ID_PARAM"
const val RELATED_OBJECT_ID_PARAM = "RELATED_OBJECT_ID_PARAM"

fun NavGraphBuilder.relationForm(
    saveObject: () -> Unit,
) {
    dialog(
        route = "$RELATION_FORM_ROUTE?$OBJECT_ID_PARAM={$OBJECT_ID_PARAM}?$RELATED_OBJECT_ID_PARAM={$RELATED_OBJECT_ID_PARAM}",
        arguments = listOf(
            navArgument(OBJECT_ID_PARAM) {
                nullable = false
                type = NavType.LongType
            },
            navArgument(RELATED_OBJECT_ID_PARAM) {
                nullable = true
                defaultValue = null
                type = NavType.StringType
            }
        ),
    ) {
        val viewModel: RelationFormViewModel = koinViewModel()
        val display = viewModel.display.collectAsStateWithLifecycle()

        RelationForm(
            screenState = display.value,
            action = { action ->
                viewModel.onAction(action)
                when (action) {
                    RelationFormAction.SaveRelation -> saveObject()
                    else -> { /*NOTHING TO DO HERE*/
                    }
                }
            },
        )
    }
}

fun NavController.navigateToRelationForm(id: Long, relatedObjectId: Long?) {
    navigate("$RELATION_FORM_ROUTE?$OBJECT_ID_PARAM=$id?$RELATED_OBJECT_ID_PARAM=$relatedObjectId")
}

internal class RelationFormArgs private constructor(
    val objectId: Long,
    val relatedObjectId: Long?
) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                objectId = checkNotNull(savedStateHandle.get<Long>(OBJECT_ID_PARAM)),
                relatedObjectId = savedStateHandle.get<String?>(RELATED_OBJECT_ID_PARAM)
                    ?.toLongOrNull()
            )
}