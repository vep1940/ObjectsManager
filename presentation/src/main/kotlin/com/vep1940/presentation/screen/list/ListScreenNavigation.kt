package com.vep1940.presentation.screen.list

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vep1940.presentation.screen.list.model.ListScreenAction
import org.koin.androidx.compose.koinViewModel

const val LIST_SCREEN_ROUTE = "listScreen"

fun NavGraphBuilder.listScreen(
    navigateToDetailScreen: (Long?) -> Unit,
) {
    composable(LIST_SCREEN_ROUTE) {
        val viewModel: ListScreenViewModel = koinViewModel()
        val display = viewModel.display.collectAsStateWithLifecycle()

        ListScreen(
            display = display.value,
            action = { action ->
                viewModel.onAction(action)
                when (action) {
                    ListScreenAction.AddItem -> navigateToDetailScreen(null)
                    is ListScreenAction.SelectItem -> navigateToDetailScreen(action.id)
                    else -> { /*DO NOTHING*/
                    }
                }
            },
        )
    }
}