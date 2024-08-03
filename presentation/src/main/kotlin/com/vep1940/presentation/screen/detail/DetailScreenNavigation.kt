package com.vep1940.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vep1940.presentation.screen.detail.model.DetailScreenAction
import org.koin.androidx.compose.koinViewModel

const val DETAIL_SCREEN_ROUTE = "detailScreen"
internal const val DETAIL_OBJECT_ID_PARAM = "DETAIL_OBJECT_ID_PARAM"

fun NavGraphBuilder.detailScreen(
    openObjectForm: (Long) -> Unit,
) {
    composable(
        route = "$DETAIL_SCREEN_ROUTE/{$DETAIL_OBJECT_ID_PARAM}",
        arguments = listOf(
            navArgument(DETAIL_OBJECT_ID_PARAM) { type = NavType.LongType }
        )
    ) {
        val viewModel: DetailScreenViewModel = koinViewModel()
        val display = viewModel.display.collectAsStateWithLifecycle()

        DetailScreen(
            display = display.value,
            action = { action ->
                viewModel.onAction(action)
                when (action) {
                    is DetailScreenAction.ModifyObject -> openObjectForm(action.id)
                    else -> { /*NOTHING TO DO HERE*/
                    }
                }
            }
        )
    }
}

fun NavController.navigateToDetailScreen(objectId: Long) {
    navigate("$DETAIL_SCREEN_ROUTE/$objectId")
}

internal class DetailScreenArgs private constructor(val objectId: Long) {
    constructor(savedStateHandle: SavedStateHandle) :
            this((checkNotNull(savedStateHandle[DETAIL_OBJECT_ID_PARAM]) as String).toLong())
}